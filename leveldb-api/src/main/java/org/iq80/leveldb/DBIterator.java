/*
 * Copyright (C) 2011 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iq80.leveldb;

import java.io.Closeable;
import java.util.Iterator;
import java.util.Map;

/**
 * @author <a href="http://hiramchirino.com">Hiram Chirino</a>
 */
public interface DBIterator
        extends Iterator<Map.Entry<byte[], byte[]>>, Closeable
{
    /**
     * Repositions the iterator so the key of the next BlockElement
     * returned greater than or equal to the specified targetKey.
     */
    void seek(byte[] key);

    /**
     * seek to the last key that is less than or equal to the target key.
     * { @link <a href="https://github.com/facebook/rocksdb/wiki/SeekForPrev"></a> }
     */
    void seekForPrev(byte[] key);

    /**
     * Repositions the iterator so is is at the beginning of the Database.
     */
    void seekToFirst();

    /**
     * @see #key()
     * @see #value()
     * Returns the next element in the iteration, without advancing the iteration.
     */
    @Deprecated
    Map.Entry<byte[], byte[]> peekNext();

    /**
     * @return true if there is a previous entry in the iteration.
     */
    boolean hasPrev();

    /**
     * @return the previous element in the iteration and rewinds the iteration.
     */
    Map.Entry<byte[], byte[]> prev();

    /**
     * @see #key()
     * @see #value()
     * @return the previous element in the iteration, without rewinding the iteration.
     */
    @Deprecated
    Map.Entry<byte[], byte[]> peekPrev();

    /**
     * Repositions the iterator so it is at the end of of the Database.
     */
    void seekToLast();

    /**
     * An iterator is either positioned at a key/value pair, or
     *  not valid.  This method returns true iff the iterator is valid.
     * @return
     */
    boolean  Valid();

    /**
     * Return the key for the current entry.  The underlying storage for
     * the returned slice is valid only until the next modification of
     * the iterator.
     * REQUIRES: Valid()
     */
   byte[] key();

    /**
     * Return the value for the current entry.  The underlying storage for
     * the returned slice is valid only until the next modification of
     * the iterator.
     * REQUIRES: Valid()
     */

    byte[] value();
}

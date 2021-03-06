/*
 * MIT License
 *
 * Copyright (c) 2019 Bjarne Koll
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.lynxplay.idonis.core.dialect.promise;

import me.lynxplay.idonis.dialect.promise.StatementPromise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * A valid implementation of the statement promise, which will delegate to {@link Connection#prepareStatement(String)}
 */
public class ValidStatementPromise implements StatementPromise {

    private String rawContent;
    private Map<Integer, List<Integer>> replacement;

    /**
     * Creates a new {@link ValidStatementPromise} which will try to create the {@link PreparedStatement}
     *
     * @param rawContent the raw string content
     * @param replacement the variable replacements defined in the comment
     */
    public ValidStatementPromise(String rawContent, Map<Integer, List<Integer>> replacement) {
        this.rawContent = rawContent;
        this.replacement = replacement;
    }

    @Override
    public PreparedStatement prepare(Connection connection) throws SQLException {
        return new ValidStatementWrapper(connection.prepareStatement(this.rawContent), replacement);
    }

    @Override
    public boolean isPresent() {
        return true;
    }
}

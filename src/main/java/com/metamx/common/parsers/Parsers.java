/*
 * Copyright 2011,2012 Metamarkets Group Inc.
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

package com.metamx.common.parsers;


import com.google.common.base.Function;
import com.metamx.common.exception.FormattedException;

import java.util.Map;

public class Parsers
{
  public static <K, V> Function<String, Map<K, V>> toFunction(final Parser p) {

    /**
     * Creates a Function object wrapping the given parser.
     * Parser inputs that throw an FormattedException are mapped to null.
     */
    return new Function<String, Map<K, V>>() {
      @Override
      public Map<K, V> apply(String input) {
        try {
          return p.parse(input);
        }
        catch(FormattedException e) {
          return null;
        }
      }
    };
  }
}

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

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.metamx.common.exception.FormattedException;

import java.util.List;
import java.util.Map;

/**
 */
public class ToLowerCaseParser implements Parser<String, Object>
{
  private final Parser baseParser;

  public ToLowerCaseParser(Parser baseParser)
  {
    this.baseParser = baseParser;
  }

  @Override
  public Map parse(String input) throws FormattedException
  {
    Map<String, Object> line = baseParser.parse(input);
    Map<String, Object> retVal = Maps.newLinkedHashMap();
    for (Map.Entry<String, Object> entry : line.entrySet()) {
      String k = entry.getKey().toLowerCase();

      if(retVal.containsKey(k)) {
        // Duplicate key, case-insensitively
        throw new FormattedException.Builder()
            .withErrorCode(FormattedException.ErrorCode.UNPARSABLE_ROW)
            .withMessage(String.format("Duplicate key [%s]", k))
            .build();
      }

      retVal.put(k, entry.getValue());
    }
    return retVal;
  }

  @Override
  public void setFieldNames(Iterable<String> fieldNames)
  {
    baseParser.setFieldNames(fieldNames);
  }

  @Override
  public List<String> getFieldNames()
  {
    return baseParser.getFieldNames();
  }
}

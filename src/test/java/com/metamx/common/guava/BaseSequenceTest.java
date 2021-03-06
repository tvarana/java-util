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

package com.metamx.common.guava;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 */
public class BaseSequenceTest
{
  @Test
  public void testSanity() throws Exception
  {
    final List<Integer> vals = Arrays.asList(1, 2, 3, 4, 5);
    SequenceTestHelper.testAll(BaseSequence.simple(vals), vals);
  }

  @Test
  public void testNothing() throws Exception
  {
    final List<Integer> vals = Arrays.asList();
    SequenceTestHelper.testAll(BaseSequence.simple(vals), vals);
  }
}

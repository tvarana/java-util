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

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

/**
 */
public class FunctionalIteratorTest
{
  @Test
  public void testTransform() throws Exception
  {
    Assert.assertEquals(
        Lists.<Integer>newArrayList(
            FunctionalIterator.create(Arrays.asList("1", "2", "3").iterator())
                              .transform(
                                  new Function<String, Integer>()
                                  {
                                    @Override
                                    public Integer apply(String input)
                                    {
                                      return Integer.parseInt(input);
                                    }
                                  }
                              )
        ),
        Arrays.asList(1, 2, 3)
    );
  }

  @Test
  public void testTransformCat() throws Exception
  {
    Assert.assertEquals(
        Lists.<String>newArrayList(
            FunctionalIterator.create(Arrays.asList("1,2", "3,4", "5,6").iterator())
                              .transformCat(
                                  new Function<String, Iterator<String>>()
                                  {
                                    @Override
                                    public Iterator<String> apply(String input)
                                    {
                                      return Splitter.on(",").split(input).iterator();
                                    }
                                  }
                              )
        ),
        Arrays.asList("1", "2", "3", "4", "5", "6")
    );
  }

  @Test
  public void testKeep() throws Exception
  {
    Assert.assertEquals(
        Lists.<Integer>newArrayList(
            FunctionalIterator.create(Arrays.asList("1", "2", "3").iterator())
                              .keep(
                                  new Function<String, Integer>()
                                  {
                                    @Override
                                    public Integer apply(String input)
                                    {
                                      if ("2".equals(input)) {
                                        return null;
                                      }
                                      return Integer.parseInt(input);
                                    }
                                  }
                              )
        ),
        Arrays.asList(1, 3)
    );
  }

  @Test
  public void testFilter() throws Exception
  {
    Assert.assertEquals(
        Lists.<String>newArrayList(
            FunctionalIterator.create(Arrays.asList("1", "2", "3").iterator())
                              .filter(
                                  new Predicate<String>()
                                  {
                                    @Override
                                    public boolean apply(String input)
                                    {
                                      return !"2".equals(input);
                                    }
                                  }
                              )
        ),
        Arrays.asList("1", "3")
    );
  }

  @Test
  public void testDrop() throws Exception
  {
    Assert.assertEquals(
        Lists.<String>newArrayList(
            FunctionalIterator.create(Arrays.asList("1", "2", "3").iterator())
                              .drop(2)
        ),
        Arrays.asList("3")
    );
  }
}

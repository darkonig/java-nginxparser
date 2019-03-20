/*
 * Copyright 2014 Alexey Plotnik
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

package com.darkonig.parser.nginxparser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class TricksTest  extends ParseTestBase {

    @Test
    public void testC1() throws Exception {
        Iterator<NgxEntry> it = parse("tricks/c1.conf").getEntries().iterator();
        NgxBlock loc = (NgxBlock) it.next();
        TestUtils.assertBlock(loc, "events");

        Iterator<NgxEntry> it2 = loc.getEntries().iterator();
        TestUtils.assertParam(it2.next(), "worker_connections", "2048");
        TestUtils.assertParam(it2.next(), "use", "epoll");
        Assert.assertFalse(it2.hasNext());
        Assert.assertFalse(it.hasNext());
    }
}

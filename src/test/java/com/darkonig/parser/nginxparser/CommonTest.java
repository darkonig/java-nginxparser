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

public class CommonTest extends ParseTestBase {

    @Test
    public void testC1() throws Exception {
        Iterator<NgxEntry> it = parse("common/c1.conf").getEntries().iterator();

        TestUtils.assertParam(it.next(), "user", "nginx");
        TestUtils.assertParam(it.next(), "worker_processes", "2");
        TestUtils.assertParam(it.next(), "timer_resolution", "100ms");
        TestUtils.assertParam(it.next(), "worker_rlimit_nofile", "8192");
        TestUtils.assertParam(it.next(), "worker_priority", "-10");
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testC2() throws Exception {
        Iterator<NgxEntry> it = parse("common/c2.conf").getEntries().iterator();
        TestUtils.assertParam(it.next(), "error_log", "/var/log/nginx/error.log", "warn");
        TestUtils.assertParam(it.next(), "pid", "/var/run/nginx.pid");
        NgxBlock events = TestUtils.assertBlock(it.next(), "events");
        Iterator<NgxEntry> eventsIt = events.getEntries().iterator();
        TestUtils.assertParam(eventsIt.next(), "worker_connections", "2048");
        TestUtils.assertParam(eventsIt.next(), "use", "epoll");
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testC3() throws Exception {
        Iterator<NgxEntry> it = parse("common/c3.conf").getEntries().iterator();

        TestUtils.assertParam(it.next(), "user", "nginx");
        TestUtils.assertComment(it.next(), "worker_processes  2;");
        TestUtils.assertParam(it.next(), "worker_priority", "-10");
        TestUtils.assertParam(it.next(), "proxy_pass", "http://unix:/opt/apps/ipn/ipn.sock:/");
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testC4() throws Exception {
        Iterator<NgxEntry> it = parse("common/c4.conf").getEntries().iterator();

        TestUtils.assertBlock(it.next(), "location", "@backend");
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testC5() throws Exception {
        Iterator<NgxEntry> it = parse("common/c5.conf").getEntries().iterator();

        TestUtils.assertBlock(it.next(), "location", "~", "/\\.");
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testC6() throws Exception {
        Iterator<NgxEntry> it = parse("common/c6.conf").getEntries().iterator();

        TestUtils.assertBlock(it.next(), "location", "~*", "\\.(?:ico|css|js|gif|jpe?g|png)$");
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testC7() throws Exception {
        Iterator<NgxEntry> it = parse("common/c7.conf").getEntries().iterator();

        TestUtils.assertParam(it.next(), "fastcgi_split_path_info", "^(.+\\.php)(/.+)$");
        Assert.assertFalse(it.hasNext());
    }
}

package com.dk.parser.nginxparser;

import org.junit.Assert;
import org.junit.Test;
import java.util.Iterator;

public class IfTest extends ParseTestBase {

    @Test
    public void testC1() throws Exception {
        Iterator<NgxEntry> it = parse("if/c1.conf").getEntries().iterator();
        NgxBlock block = (NgxBlock) it.next();
        Iterator<NgxEntry> it2 = block.iterator();
        TestUtils.assertIfBlock(it2.next(), "if", "$request_method", "=", "POST");
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testC2() throws Exception {
        TestUtils.assertIfBlock(extractIF("if/c2.conf"), "if", "$query_string", "!=", "\"\"");
    }

    @Test
    public void testC3() throws Exception {
        TestUtils.assertIfBlock(extractIF("if/c3.conf"), "if", "$request_uri", "!~", "\\/$");
    }

    @Test
    public void testC4() throws Exception {
        TestUtils.assertIfBlock(extractIF("if/c4.conf"), "if", "$request_uri", "~*",
                "\"(sitemap(_index)?\\.xml(\\.gz)?|[a-z0-9_\\-]+-sitemap([0-9]+)?\\.xml(\\.gz)?)\"");
    }

    @Test
    public void testC5() throws Exception {
        TestUtils.assertIfBlock(extractIF("if/c5.conf"), "if", "$request_uri", "~*",
                "(sitemap(_index)?\\.xml(\\.gz)?|[a-z0-9_\\-]+-sitemap([0-9]+)?\\.xml(\\.gz)?)");
    }

    @Test
    public void testC6() throws Exception {
        TestUtils.assertIfBlock(extractIF("if/c6.conf"), "if", "-f",
                "\"$document_root/wp-content/cache/page_enhanced/$host/$request_uri/_index$w3tc_ua$w3tc_ref$w3tc_ssl.html$w3tc_enc\"");
    }

    @Test
    public void testC7() throws Exception {
        NgxBlock entry = (NgxBlock) extractIF("if/c7.conf");
        TestUtils.assertIfBlock(entry, "if", "-f",
                "\"$document_root/wp-content/cache/page_enhanced/$host/$request_uri/_index$w3tc_ua$w3tc_ref$w3tc_ssl.html$w3tc_enc\"");

        NgxIfBlock next = (NgxIfBlock) entry.getEntries().iterator().next();
        System.out.println(next.getEntries());
    }

    private NgxEntry extractIF(String path) throws Exception {
        Iterator<NgxEntry> it = parse(path).getEntries().iterator();
        NgxBlock block = (NgxBlock) it.next();
        Iterator<NgxEntry> it2 = block.iterator();
        return it2.next();
    }
}

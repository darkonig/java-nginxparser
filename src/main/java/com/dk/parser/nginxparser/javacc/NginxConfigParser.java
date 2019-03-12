/* NginxConfigParser.java */
/* Generated By:JavaCC: Do not edit this line. NginxConfigParser.java */
package com.dk.parser.nginxparser.javacc;
import com.dk.parser.nginxparser.*;
import com.github.odiszapc.nginxparser.*;
@SuppressWarnings("ConstantConditions")
public class NginxConfigParser implements NginxConfigParserConstants {
  private boolean isDebug = false;

  public void setDebug(boolean val) {
    isDebug = val;
  }

  private void debug(String message) {
    debug(message, false);
  }

  private void debugLn(String message) {
    debug(message, true);
  }

  private void debug(String message, boolean appendLineEnding) {
    if (isDebug) {
      System.out.print(message);
      if (appendLineEnding) System.out.println();
    }
  }

/*
  Main method
*/
  final public NgxConfig parse() throws ParseException {NgxConfig config;
    config = statements();
{if ("" != null) return config;}
    throw new Error("Missing return statement in function");
  }

  final public NgxConfig statements() throws ParseException {NgxConfig config = new NgxConfig();
  NgxEntry curEntry;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case STRING:
      case QUOTED_STRING:
      case SINGLE_QUOTED_STRING:
      case SINGLE_LINE_COMMENT:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      if (jj_2_1(4)) {
        curEntry = param();
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case STRING:
        case QUOTED_STRING:
        case SINGLE_QUOTED_STRING:{
          curEntry = block();
          break;
          }
        case SINGLE_LINE_COMMENT:{
          curEntry = comment();
          break;
          }
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
config.addEntry(curEntry);
    }
{if ("" != null) return config;}
    throw new Error("Missing return statement in function");
  }

/*
  Block

  Example:
  http {
    ...
  }
*/
  final public NgxBlock block() throws ParseException {String blockName = "";
  NgxBlock block = new NgxBlock();
  NgxToken curToken;
  NgxEntry curEntry;
    curToken = id();
blockName += token.image+" ";
    block.getTokens().add(curToken);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case STRING:
      case QUOTED_STRING:
      case SINGLE_QUOTED_STRING:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      curToken = id();
blockName += token.image+" ";
      block.getTokens().add(curToken);
    }
debugLn("BLOCK=" + blockName + "{");
    jj_consume_token(LBRACE);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IF:
      case STRING:
      case QUOTED_STRING:
      case SINGLE_QUOTED_STRING:
      case SINGLE_LINE_COMMENT:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      if (jj_2_2(4)) {
        curEntry = param();
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case STRING:
        case QUOTED_STRING:
        case SINGLE_QUOTED_STRING:{
          curEntry = block();
          break;
          }
        case SINGLE_LINE_COMMENT:{
          curEntry = comment();
          break;
          }
        case IF:{
          curEntry = if_block();
          break;
          }
        default:
          jj_la1[4] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
block.addEntry(curEntry);
    }
    jj_consume_token(RBRACE);
debugLn("}");
    {if ("" != null) return block;}
    throw new Error("Missing return statement in function");
  }

/*
  "if" statement

  Example:
  if ($arg_val !~ '^\d+$') {
     return 400;
     break;
  }
*/
  final public NgxBlock if_block() throws ParseException {NgxIfBlock block = new NgxIfBlock();
  NgxEntry curEntry = null;
    jj_consume_token(IF);
debug(token.image + " ");
    block.addValue(new NgxToken(token.image));
    jj_consume_token(IF_BODY);
debug(token.image + " ");
    block.addValue(new NgxToken(token.image));
    jj_consume_token(LBRACE);
debugLn(token.image);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case STRING:
      case QUOTED_STRING:
      case SINGLE_QUOTED_STRING:
      case SINGLE_LINE_COMMENT:{
        ;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        break label_4;
      }
      if (jj_2_3(4)) {
        curEntry = param();
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case STRING:
        case QUOTED_STRING:
        case SINGLE_QUOTED_STRING:{
          curEntry = block();
          break;
          }
        case SINGLE_LINE_COMMENT:{
          curEntry = comment();
          break;
          }
        default:
          jj_la1[6] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
block.addEntry(curEntry);
    }
    jj_consume_token(RBRACE);
debugLn(token.image);
    {if ("" != null) return block;}
    throw new Error("Missing return statement in function");
  }

/*
  Single line parameter

  Examples:
  listen 80;
  server_name localhost;
  root /var/www;
  index index.html index.htm;
  array_map '[$array_it]' $list;
  echo_exec /safe-memc?cmd=incr&key=$arg_key&val=$arg_val;
*/
  final public NgxParam param() throws ParseException {NgxParam param = new NgxParam();
  NgxToken curToken = null;
    curToken = id();
debug("KEY=" + token.image +", VALUE=");
    param.getTokens().add(curToken);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NUMBER:
      case STRING:
      case QUOTED_STRING:
      case SINGLE_QUOTED_STRING:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_5;
      }
      curToken = value();
debug(token.image + " | ");
      param.getTokens().add(curToken);
    }
debugLn("");
    jj_consume_token(SEMICOLON);
{if ("" != null) return param;}
    throw new Error("Missing return statement in function");
  }

/*
  Comment
  Example:
  # Comment here
*/
  final public NgxComment comment() throws ParseException {NgxComment comment;
    jj_consume_token(SINGLE_LINE_COMMENT);
debugLn("COMMENT=" + token.image);
    {if ("" != null) return new NgxComment(token.image);}
    throw new Error("Missing return statement in function");
  }

/*
  Parameter/block names and/or values
  Example:
  # id occurs 6 times
  gzip_types text/plain application/xml application/x-javascript text/css application/json;

*/
  final public NgxToken id() throws ParseException {NgxToken val = null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case STRING:{
      jj_consume_token(STRING);
      break;
      }
    case QUOTED_STRING:{
      jj_consume_token(QUOTED_STRING);
      break;
      }
    case SINGLE_QUOTED_STRING:{
      jj_consume_token(SINGLE_QUOTED_STRING);
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return new NgxToken(token.image);};
    throw new Error("Missing return statement in function");
  }

/*
  Parameter values
*/
  final public NgxToken value() throws ParseException {NgxToken val = null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NUMBER:{
      jj_consume_token(NUMBER);
      break;
      }
    case STRING:{
      jj_consume_token(STRING);
      break;
      }
    case QUOTED_STRING:{
      jj_consume_token(QUOTED_STRING);
      break;
      }
    case SINGLE_QUOTED_STRING:{
      jj_consume_token(SINGLE_QUOTED_STRING);
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return new NgxToken(token.image);};
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_3R_8()
 {
    if (jj_3R_9()) return true;
    return false;
  }

  private boolean jj_3R_7()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(13)) {
    jj_scanpos = xsp;
    if (jj_scan_token(14)) {
    jj_scanpos = xsp;
    if (jj_scan_token(15)) return true;
    }
    }
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3R_9()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(12)) {
    jj_scanpos = xsp;
    if (jj_scan_token(13)) {
    jj_scanpos = xsp;
    if (jj_scan_token(14)) {
    jj_scanpos = xsp;
    if (jj_scan_token(15)) return true;
    }
    }
    }
    return false;
  }

  private boolean jj_3_3()
 {
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3R_6()
 {
    if (jj_3R_7()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_8()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(SEMICOLON)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public NginxConfigParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[10];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1e000,0x1e000,0xe000,0x1e400,0x1e400,0x1e000,0x1e000,0xf000,0xe000,0xf000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[3];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public NginxConfigParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public NginxConfigParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new NginxConfigParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public NginxConfigParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new NginxConfigParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public NginxConfigParser(NginxConfigParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(NginxConfigParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[19];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 10; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 19; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 3; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}

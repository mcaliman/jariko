// Generated from MuteParser.g4 by ANTLR 4.7.1
package com.smeup.rpgparser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MuteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OPEN_PAREN=1, CLOSE_PAREN=2, VAL1=3, VAL2=4, EQ=5, NE=6, GT=7, GE=8, LT=9, 
		LE=10, COMP=11, TYPE=12, WS=13, EXP=14;
	public static final int
		RULE_muteLine = 0, RULE_muteAnnotation = 1, RULE_muteComparisonOperator = 2;
	public static final String[] ruleNames = {
		"muteLine", "muteAnnotation", "muteComparisonOperator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'VAL1'", "'VAL2'", "'(EQ)'", "'(NE)'", "'(GT)'", 
		"'(GE)'", "'(LT)'", "'(LE)'", "'COMP'", "'Type'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OPEN_PAREN", "CLOSE_PAREN", "VAL1", "VAL2", "EQ", "NE", "GT", "GE", 
		"LT", "LE", "COMP", "TYPE", "WS", "EXP"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MuteParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MuteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class MuteLineContext extends ParserRuleContext {
		public MuteAnnotationContext muteAnnotation() {
			return getRuleContext(MuteAnnotationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MuteParser.EOF, 0); }
		public MuteLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_muteLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MuteParserListener ) ((MuteParserListener)listener).enterMuteLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MuteParserListener ) ((MuteParserListener)listener).exitMuteLine(this);
		}
	}

	public final MuteLineContext muteLine() throws RecognitionException {
		MuteLineContext _localctx = new MuteLineContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_muteLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(6);
			muteAnnotation();
			setState(7);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MuteAnnotationContext extends ParserRuleContext {
		public MuteAnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_muteAnnotation; }
	 
		public MuteAnnotationContext() { }
		public void copyFrom(MuteAnnotationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MuteTypeAnnotationContext extends MuteAnnotationContext {
		public MuteTypeAnnotationContext(MuteAnnotationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MuteParserListener ) ((MuteParserListener)listener).enterMuteTypeAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MuteParserListener ) ((MuteParserListener)listener).exitMuteTypeAnnotation(this);
		}
	}
	public static class MuteComparisonAnnotationContext extends MuteAnnotationContext {
		public Token val1;
		public Token val2;
		public MuteComparisonOperatorContext cp;
		public TerminalNode VAL1() { return getToken(MuteParser.VAL1, 0); }
		public TerminalNode VAL2() { return getToken(MuteParser.VAL2, 0); }
		public TerminalNode COMP() { return getToken(MuteParser.COMP, 0); }
		public List<TerminalNode> EXP() { return getTokens(MuteParser.EXP); }
		public TerminalNode EXP(int i) {
			return getToken(MuteParser.EXP, i);
		}
		public MuteComparisonOperatorContext muteComparisonOperator() {
			return getRuleContext(MuteComparisonOperatorContext.class,0);
		}
		public MuteComparisonAnnotationContext(MuteAnnotationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MuteParserListener ) ((MuteParserListener)listener).enterMuteComparisonAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MuteParserListener ) ((MuteParserListener)listener).exitMuteComparisonAnnotation(this);
		}
	}

	public final MuteAnnotationContext muteAnnotation() throws RecognitionException {
		MuteAnnotationContext _localctx = new MuteAnnotationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_muteAnnotation);
		try {
			setState(16);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAL1:
				_localctx = new MuteComparisonAnnotationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(9);
				match(VAL1);
				setState(10);
				((MuteComparisonAnnotationContext)_localctx).val1 = match(EXP);
				setState(11);
				match(VAL2);
				setState(12);
				((MuteComparisonAnnotationContext)_localctx).val2 = match(EXP);
				setState(13);
				match(COMP);
				setState(14);
				((MuteComparisonAnnotationContext)_localctx).cp = muteComparisonOperator();
				}
				break;
			case EOF:
				_localctx = new MuteTypeAnnotationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MuteComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(MuteParser.EQ, 0); }
		public TerminalNode NE() { return getToken(MuteParser.NE, 0); }
		public TerminalNode LT() { return getToken(MuteParser.LT, 0); }
		public TerminalNode LE() { return getToken(MuteParser.LE, 0); }
		public TerminalNode GT() { return getToken(MuteParser.GT, 0); }
		public TerminalNode GE() { return getToken(MuteParser.GE, 0); }
		public MuteComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_muteComparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MuteParserListener ) ((MuteParserListener)listener).enterMuteComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MuteParserListener ) ((MuteParserListener)listener).exitMuteComparisonOperator(this);
		}
	}

	public final MuteComparisonOperatorContext muteComparisonOperator() throws RecognitionException {
		MuteComparisonOperatorContext _localctx = new MuteComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_muteComparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NE) | (1L << GT) | (1L << GE) | (1L << LT) | (1L << LE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\20\27\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\23\n\3\3\4\3"+
		"\4\3\4\2\2\5\2\4\6\2\3\3\2\7\f\2\24\2\b\3\2\2\2\4\22\3\2\2\2\6\24\3\2"+
		"\2\2\b\t\5\4\3\2\t\n\7\2\2\3\n\3\3\2\2\2\13\f\7\5\2\2\f\r\7\20\2\2\r\16"+
		"\7\6\2\2\16\17\7\20\2\2\17\20\7\r\2\2\20\23\5\6\4\2\21\23\3\2\2\2\22\13"+
		"\3\2\2\2\22\21\3\2\2\2\23\5\3\2\2\2\24\25\t\2\2\2\25\7\3\2\2\2\3\22";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
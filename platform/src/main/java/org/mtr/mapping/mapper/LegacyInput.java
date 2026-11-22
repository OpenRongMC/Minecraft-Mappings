package org.mtr.mapping.mapper;

import java.util.HashMap;
import java.util.Map;
import com.mojang.blaze3d.platform.InputConstants;

/** Preserves the public MTR key/button contract while Minecraft uses SDL scancodes. */
public final class LegacyInput {
    private static final Map<Integer,Integer> TO_NATIVE=new HashMap<>(), TO_LEGACY=new HashMap<>();
    static {
        for(int i=0;i<26;i++) bind(65+i,InputConstants.KEY_A+i);
        bind(48,InputConstants.KEY_0);
        for(int i=1;i<10;i++) bind(48+i,InputConstants.KEY_1+i-1);
        for(int i=0;i<12;i++) bind(290+i,InputConstants.KEY_F1+i);
        for(int i=0;i<12;i++) bind(302+i,InputConstants.KEY_F13+i);
        bind(32,InputConstants.KEY_SPACE);bind(39,InputConstants.KEY_APOSTROPHE);
        bind(44,InputConstants.KEY_COMMA);bind(45,InputConstants.KEY_MINUS);bind(46,InputConstants.KEY_PERIOD);
        bind(47,InputConstants.KEY_SLASH);bind(59,InputConstants.KEY_SEMICOLON);bind(61,InputConstants.KEY_EQUALS);
        bind(91,InputConstants.KEY_LBRACKET);bind(92,InputConstants.KEY_BACKSLASH);bind(93,InputConstants.KEY_RBRACKET);bind(96,InputConstants.KEY_GRAVE);
        int[] navigation={InputConstants.KEY_ESCAPE,InputConstants.KEY_RETURN,InputConstants.KEY_TAB,InputConstants.KEY_BACKSPACE,InputConstants.KEY_INSERT,InputConstants.KEY_DELETE,InputConstants.KEY_RIGHT,InputConstants.KEY_LEFT,InputConstants.KEY_DOWN,InputConstants.KEY_UP,InputConstants.KEY_PAGEUP,InputConstants.KEY_PAGEDOWN,InputConstants.KEY_HOME,InputConstants.KEY_END};
        for(int i=0;i<navigation.length;i++) bind(256+i,navigation[i]);
        int[] locks={InputConstants.KEY_CAPSLOCK,InputConstants.KEY_SCROLLLOCK,InputConstants.KEY_NUMLOCK,InputConstants.KEY_PRINTSCREEN,InputConstants.KEY_PAUSE};
        for(int i=0;i<locks.length;i++) bind(280+i,locks[i]);
        bind(320,InputConstants.KEY_NUMPAD0);for(int i=1;i<10;i++) bind(320+i,InputConstants.KEY_NUMPAD1+i-1);
        bind(330,99);bind(331,84);bind(332,InputConstants.KEY_MULTIPLY);bind(333,86);bind(334,InputConstants.KEY_ADD);bind(335,InputConstants.KEY_NUMPADENTER);bind(336,InputConstants.KEY_NUMPADEQUALS);
        int[] modifiers={InputConstants.KEY_LSHIFT,InputConstants.KEY_LCONTROL,InputConstants.KEY_LALT,InputConstants.KEY_LGUI,InputConstants.KEY_RSHIFT,InputConstants.KEY_RCONTROL,InputConstants.KEY_RALT,InputConstants.KEY_RGUI};
        for(int i=0;i<modifiers.length;i++)bind(340+i,modifiers[i]);bind(348,101);
    }
    private static void bind(int legacy,int nativeKey){TO_NATIVE.put(legacy,nativeKey);TO_LEGACY.put(nativeKey,legacy);}
    public static int keyToNative(int key){return TO_NATIVE.getOrDefault(key,0);}
    public static int keyToLegacy(int key){return TO_LEGACY.getOrDefault(key,-1);}
    public static int modifiersToLegacy(int value){return ((value&InputConstants.MOD_SHIFT)!=0?1:0)|((value&InputConstants.MOD_CONTROL)!=0?2:0)|((value&InputConstants.MOD_ALT)!=0?4:0)|((value&InputConstants.MOD_SUPER)!=0?8:0)|((value&InputConstants.MOD_CAPS_LOCK)!=0?16:0)|((value&InputConstants.MOD_NUM_LOCK)!=0?32:0);}
    public static int modifiersToNative(int value){return ((value&1)!=0?InputConstants.MOD_SHIFT:0)|((value&2)!=0?InputConstants.MOD_CONTROL:0)|((value&4)!=0?InputConstants.MOD_ALT:0)|((value&8)!=0?InputConstants.MOD_SUPER:0)|((value&16)!=0?InputConstants.MOD_CAPS_LOCK:0)|((value&32)!=0?InputConstants.MOD_NUM_LOCK:0);}
    public static int mouseToLegacy(int button){return button==1?0:button==3?1:button==2?2:button-1;}
    public static int mouseToNative(int button){return button==0?1:button==1?3:button==2?2:button+1;}
    private LegacyInput(){}
}

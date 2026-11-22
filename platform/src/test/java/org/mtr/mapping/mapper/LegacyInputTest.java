package org.mtr.mapping.mapper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LegacyInputTest {
    @Test void preservesEverySupportedLegacyKeyboardBinding() {
        for(int first:new int[]{48,65,256,280,290,302,320,340}) {
            int count=switch(first){case 48,320->10;case 65->26;case 256->14;case 280->5;case 290,302->12;case 340->9;default->throw new AssertionError();};
            for(int key=first;key<first+count;key++)assertEquals(key,LegacyInput.keyToLegacy(LegacyInput.keyToNative(key)),"legacy key "+key);
        }
        for(int key:new int[]{32,39,44,45,46,47,59,61,91,92,93,96,330,331,332,333,334,335,336})
            assertEquals(key,LegacyInput.keyToLegacy(LegacyInput.keyToNative(key)),"legacy key "+key);
        assertEquals(-1,LegacyInput.keyToLegacy(0));
    }
    @Test void preservesMouseButtonOrderIncludingMiddleAndExtraButtons() {
        for(int button=0;button<8;button++)assertEquals(button,LegacyInput.mouseToLegacy(LegacyInput.mouseToNative(button)));
        assertEquals(1,LegacyInput.mouseToNative(0));assertEquals(3,LegacyInput.mouseToNative(1));assertEquals(2,LegacyInput.mouseToNative(2));
    }
    @Test void preservesAllModifierCombinations() {
        for(int modifiers=0;modifiers<64;modifiers++)assertEquals(modifiers,LegacyInput.modifiersToLegacy(LegacyInput.modifiersToNative(modifiers)));
    }
}

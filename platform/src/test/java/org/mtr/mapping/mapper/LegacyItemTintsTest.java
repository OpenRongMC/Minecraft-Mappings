package org.mtr.mapping.mapper;

import java.util.List;
import java.util.Optional;
import net.minecraft.client.color.item.Constant;
import net.minecraft.client.renderer.item.CompositeModel;
import net.minecraft.client.renderer.item.CuboidItemModelWrapper;
import net.minecraft.resources.Identifier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LegacyItemTintsTest {
    @Test void restoresLegacyColorsWithoutDroppingModelsOrTintIndices() {
        var model=new CuboidItemModelWrapper.Unbaked(Identifier.parse("mtr:item/driver_key"),Optional.empty(),List.of(new Constant(-1),new Constant(-1)));
        var original=new CompositeModel.Unbaked(List.of(model),Optional.empty());
        var result=(CompositeModel.Unbaked)LegacyItemTints.adapt(original,(stack,index)->index==0?0x112233:0x445566);
        var tinted=(CuboidItemModelWrapper.Unbaked)result.models().getFirst();
        assertEquals(model.model(),tinted.model());assertEquals(model.transformation(),tinted.transformation());
        assertEquals(2,tinted.tints().size());
        assertEquals(0xFF112233,tinted.tints().get(0).calculate(null,null,null));
        assertEquals(0xFF445566,tinted.tints().get(1).calculate(null,null,null));
        assertNotSame(model,tinted);
    }
}

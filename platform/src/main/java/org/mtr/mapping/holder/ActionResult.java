package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResult.Success;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ActionResult extends HolderBase<InteractionResult> {
   @Nonnull
   public static final ActionResult SUCCESS = new ActionResult(InteractionResult.SUCCESS);
   @Nonnull
   public static final ActionResult FAIL = new ActionResult(InteractionResult.FAIL);
   @Nonnull
   public static final ActionResult PASS = new ActionResult(InteractionResult.PASS);
   @Nonnull
   public static final ActionResult CONSUME = new ActionResult(InteractionResult.CONSUME);
   @Nonnull
   public static final ActionResult CONSUME_PARTIAL = new ActionResult(InteractionResult.TRY_WITH_EMPTY_HAND);

   public ActionResult(InteractionResult data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static Success getSuccessServerMapped() {
      return InteractionResult.SUCCESS_SERVER;
   }

   @Nonnull
   @MappedMethod
   public static ActionResult getFailMapped() {
      return new ActionResult(InteractionResult.FAIL);
   }

   @Nonnull
   @MappedMethod
   public static ActionResult getPassMapped() {
      return new ActionResult(InteractionResult.PASS);
   }

   
}

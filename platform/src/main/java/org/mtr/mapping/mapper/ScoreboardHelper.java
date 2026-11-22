package org.mtr.mapping.mapper;

import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.numbers.BlankFormat;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreAccess;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Scoreboard;
import org.mtr.mapping.holder.ScoreboardCriterion;
import org.mtr.mapping.holder.ScoreboardCriterionRenderType;
import org.mtr.mapping.holder.ScoreboardObjective;
import org.mtr.mapping.holder.Text;
import org.mtr.mapping.tool.DummyClass;

public final class ScoreboardHelper extends DummyClass {
   @Nullable
   @MappedMethod
   public static ScoreboardObjective getScoreboardObjective(Scoreboard scoreboard, String name) {
      Objective scoreboardObjective = ((net.minecraft.world.scores.Scoreboard)scoreboard.data).getObjective(name);
      return scoreboardObjective == null ? null : new ScoreboardObjective(scoreboardObjective);
   }

   @MappedMethod
   public static ScoreboardObjective addObjective(
      Scoreboard scoreboard,
      String name,
      ScoreboardCriterion scoreboardCriterion,
      Text displayName,
      ScoreboardCriterionRenderType scoreboardCriterionRenderType
   ) {
      return new ScoreboardObjective(
         ((net.minecraft.world.scores.Scoreboard)scoreboard.data)
            .addObjective(
               name, (ObjectiveCriteria)scoreboardCriterion.data, (Component)displayName.data, scoreboardCriterionRenderType.data, true, BlankFormat.INSTANCE
            )
      );
   }

   @MappedMethod
   public static int getPlayerScore(Scoreboard scoreboard, String playerName, ScoreboardObjective scoreboardObjective) {
      return getOrCreateScore(scoreboard, playerName, scoreboardObjective).get();
   }

   @MappedMethod
   public static void setPlayerScore(Scoreboard scoreboard, String playerName, ScoreboardObjective scoreboardObjective, int amount) {
      getOrCreateScore(scoreboard, playerName, scoreboardObjective).set(amount);
   }

   @MappedMethod
   public static void incrementPlayerScore(Scoreboard scoreboard, String playerName, ScoreboardObjective scoreboardObjective, int amount) {
      getOrCreateScore(scoreboard, playerName, scoreboardObjective).add(amount);
   }

   private static ScoreAccess getOrCreateScore(Scoreboard scoreboard, String playerName, ScoreboardObjective scoreboardObjective) {
      return ((net.minecraft.world.scores.Scoreboard)scoreboard.data)
         .getOrCreatePlayerScore(ScoreHolder.forNameOnly(playerName), (Objective)scoreboardObjective.data);
   }
}

package org.mtr.mapping.render.shader;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL33;

public final class ShaderProgram implements AutoCloseable {
   private int programId;
   private final int modelViewMatLocation;
   private final int projectionMatLocation;
   private final int modelMatLocation;
   private final int colorModulatorLocation;
   private final int cutoutThresholdLocation;
   private static final String VERTEX_SHADER = "#version 330\nlayout(location = 0) in vec3 Position;\nlayout(location = 1) in vec4 Color;\nlayout(location = 2) in vec2 UV0;\nlayout(location = 3) in ivec2 UV1;\nlayout(location = 4) in ivec2 UV2;\nlayout(location = 5) in vec3 Normal;\n\nuniform mat4 ModelViewMat;\nuniform mat4 ProjMat;\nuniform mat4 ModelMat;\n\nout vec4 vertexColor;\nout vec2 texCoord0;\n\nvoid main() {\n\tgl_Position = ProjMat * ModelViewMat * ModelMat * vec4(Position, 1.0);\n\tvertexColor = Color;\n\ttexCoord0 = UV0;\n}\n";
   private static final String FRAGMENT_SHADER = "#version 330\nuniform sampler2D Sampler0;\nuniform vec4 ColorModulator;\nuniform float CutoutThreshold;\n\nin vec4 vertexColor;\nin vec2 texCoord0;\n\nout vec4 fragColor;\n\nvoid main() {\n\tvec4 color = texture(Sampler0, texCoord0) * vertexColor * ColorModulator;\n\tif (color.a < CutoutThreshold) {\n\t\tdiscard;\n\t}\n\tfragColor = color;\n}\n";

   public ShaderProgram() {
      int vertexShader = compile(
         35633,
         "#version 330\nlayout(location = 0) in vec3 Position;\nlayout(location = 1) in vec4 Color;\nlayout(location = 2) in vec2 UV0;\nlayout(location = 3) in ivec2 UV1;\nlayout(location = 4) in ivec2 UV2;\nlayout(location = 5) in vec3 Normal;\n\nuniform mat4 ModelViewMat;\nuniform mat4 ProjMat;\nuniform mat4 ModelMat;\n\nout vec4 vertexColor;\nout vec2 texCoord0;\n\nvoid main() {\n\tgl_Position = ProjMat * ModelViewMat * ModelMat * vec4(Position, 1.0);\n\tvertexColor = Color;\n\ttexCoord0 = UV0;\n}\n"
      );
      int fragmentShader = compile(
         35632,
         "#version 330\nuniform sampler2D Sampler0;\nuniform vec4 ColorModulator;\nuniform float CutoutThreshold;\n\nin vec4 vertexColor;\nin vec2 texCoord0;\n\nout vec4 fragColor;\n\nvoid main() {\n\tvec4 color = texture(Sampler0, texCoord0) * vertexColor * ColorModulator;\n\tif (color.a < CutoutThreshold) {\n\t\tdiscard;\n\t}\n\tfragColor = color;\n}\n"
      );
      this.programId = GL33.glCreateProgram();
      GL33.glAttachShader(this.programId, vertexShader);
      GL33.glAttachShader(this.programId, fragmentShader);
      GL33.glBindAttribLocation(this.programId, 0, "Position");
      GL33.glBindAttribLocation(this.programId, 1, "Color");
      GL33.glBindAttribLocation(this.programId, 2, "UV0");
      GL33.glBindAttribLocation(this.programId, 3, "UV1");
      GL33.glBindAttribLocation(this.programId, 4, "UV2");
      GL33.glBindAttribLocation(this.programId, 5, "Normal");
      GL33.glLinkProgram(this.programId);
      if (GL33.glGetProgrami(this.programId, 35714) == 0) {
         String log = GL33.glGetProgramInfoLog(this.programId);
         GL33.glDeleteProgram(this.programId);
         this.programId = 0;
         GL33.glDeleteShader(vertexShader);
         GL33.glDeleteShader(fragmentShader);
         throw new IllegalStateException("Failed to link optimized renderer shader: " + log);
      } else {
         GL33.glDetachShader(this.programId, vertexShader);
         GL33.glDetachShader(this.programId, fragmentShader);
         GL33.glDeleteShader(vertexShader);
         GL33.glDeleteShader(fragmentShader);
         this.modelViewMatLocation = GL33.glGetUniformLocation(this.programId, "ModelViewMat");
         this.projectionMatLocation = GL33.glGetUniformLocation(this.programId, "ProjMat");
         this.modelMatLocation = GL33.glGetUniformLocation(this.programId, "ModelMat");
         this.colorModulatorLocation = GL33.glGetUniformLocation(this.programId, "ColorModulator");
         this.cutoutThresholdLocation = GL33.glGetUniformLocation(this.programId, "CutoutThreshold");
         int sampler0 = GL33.glGetUniformLocation(this.programId, "Sampler0");
         GL33.glUseProgram(this.programId);
         if (sampler0 >= 0) {
            GL33.glUniform1i(sampler0, 0);
         }

         GL33.glUseProgram(0);
      }
   }

   public void bind(Matrix4f modelView, Matrix4f projection, boolean cutout) {
      GL33.glUseProgram(this.programId);
      setMatrix(this.modelViewMatLocation, modelView);
      setMatrix(this.projectionMatLocation, projection);
      if (this.colorModulatorLocation >= 0) {
         GL33.glUniform4f(this.colorModulatorLocation, 1.0F, 1.0F, 1.0F, 1.0F);
      }

      if (this.cutoutThresholdLocation >= 0) {
         GL33.glUniform1f(this.cutoutThresholdLocation, cutout ? 0.1F : 0.0F);
      }
   }

   public void setModelMat(Matrix4f modelMat) {
      setMatrix(this.modelMatLocation, modelMat);
   }

   public static void unbind() {
      GL33.glUseProgram(0);
   }

   @Override
   public void close() {
      if (this.programId != 0) {
         GL33.glDeleteProgram(this.programId);
         this.programId = 0;
      }
   }

   private static int compile(int type, String source) {
      int shader = GL33.glCreateShader(type);
      GL33.glShaderSource(shader, source);
      GL33.glCompileShader(shader);
      if (GL33.glGetShaderi(shader, 35713) == 0) {
         String log = GL33.glGetShaderInfoLog(shader);
         GL33.glDeleteShader(shader);
         throw new IllegalStateException("Failed to compile optimized renderer shader: " + log);
      } else {
         return shader;
      }
   }

   private static void setMatrix(int location, Matrix4f matrix) {
      if (location >= 0) {
         float[] values = new float[16];
         matrix.get(values);
         GL33.glUniformMatrix4fv(location, false, values);
      }
   }
}

package ganymedes01.etfuturum.client.model;

import net.minecraft.client.model.ModelBase;

public class ModelRaft extends ModelBase {
//	private final ModelRenderer leftPaddle;
//	private final ModelRenderer rightPaddle;
//	private final ImmutableList<ModelRenderer> parts;
//
//	public RaftModel() {
//		this.leftPaddle = p_251383_.getChild("left_paddle");
//		this.rightPaddle = p_251383_.getChild("right_paddle");
//		this.parts = this.createPartsBuilder(p_251383_).build();
//	}
//
//	protected ImmutableList.Builder<ModelPart> createPartsBuilder(ModelRenderer p_250773_) {
//		ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder<>();
//		builder.add(p_250773_.getChild("bottom"), this.leftPaddle, this.rightPaddle);
//		return builder;
//	}
//
//	public static void createChildren(PartDefinition p_250262_) {
//		p_250262_.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -11.0F, -4.0F, 28.0F, 20.0F, 4.0F).texOffs(0, 0).addBox(-14.0F, -9.0F, -8.0F, 28.0F, 16.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -2.0F, 1.0F, 1.5708F, 0.0F, 0.0F));
//		int i = 20;
//		int j = 7;
//		int k = 6;
//		float f = -5.0F;
//		p_250262_.addOrReplaceChild("left_paddle", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F), PartPose.offsetAndRotation(3.0F, -4.0F, 9.0F, 0.0F, 0.0F, 0.19634955F));
//		p_250262_.addOrReplaceChild("right_paddle", CubeListBuilder.create().texOffs(40, 24).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F), PartPose.offsetAndRotation(3.0F, -4.0F, -9.0F, 0.0F, (float)Math.PI, 0.19634955F));
//	}
//
//	public static LayerDefinition createBodyModel() {
//		MeshDefinition meshdefinition = new MeshDefinition();
//		PartDefinition partdefinition = meshdefinition.getRoot();
//		createChildren(partdefinition);
//		return LayerDefinition.create(meshdefinition, 128, 64);
//	}
//
//	public void setupAnim(Boat p_249733_, float p_249202_, float p_252219_, float p_249366_, float p_249759_, float p_250286_) {
//		animatePaddle(p_249733_, 0, this.leftPaddle, p_249202_);
//		animatePaddle(p_249733_, 1, this.rightPaddle, p_249202_);
//	}
//
//	public ImmutableList<ModelPart> parts() {
//		return this.parts;
//	}
//
//	public void renderPaddle(EntityNewBoat boat, int paddle, float scale, float limbSwing) {
//		float f = p_250792_.getRowingTime(p_249947_, p_251990_);
//		p_248943_.xRot = Math.clampedLerp((-(float)Math.PI / 3F), -0.2617994F, (Math.sin(-f) + 1.0F) / 2.0F);
//		p_248943_.yRot = Math.clampedLerp((-(float)Math.PI / 4F), ((float)Math.PI / 4F), (Math.sin(-f + 1.0F) + 1.0F) / 2.0F);
//		if (p_249947_ == 1) {
//			p_248943_.yRot = (float)Math.PI - p_248943_.yRot;
//		}
//
//	}
}

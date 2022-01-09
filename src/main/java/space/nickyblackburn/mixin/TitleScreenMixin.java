package space.nickyblackburn.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;

import space.nickyblackburn.screens.*;
import space.nickyblackburn.utils.*;

@Mixin(value = TitleScreen.class, priority = 1001)
public class TitleScreenMixin  extends Screen{

	private int copyrightTextWidth;
	private int copyrightTextX;

	private int i;
	private String splash;
	private int copyrightWidth;
	
	private TextRenderer font;

	protected TitleScreenMixin(Text text) {
		super(text);
	
	}

	@Inject(method="init()V", at=@At("RETURN"))

	private void renderCustomWindowTitle(CallbackInfo ci) {
		MinecraftClient.getInstance().getWindow().setTitle("Blackburn Hacked 1.17-B");

		i++;

		TitleScreenOverlay overlay = new TitleScreenOverlay();
  
		if (splash == null) {
		   splash = MinecraftClient.getInstance().getSplashTextLoader().get();
		}
  
		// runs only on 2nd startup of main menu
		if(i == 1){
		   overlay.BlackburnTitleInit();
		}
		

		this.copyrightWidth = font.getWidth(Consts.copyright);
		this.copyrightTextX = width - copyrightWidth - 2;
		
		int i = 24;
		int j = this.height / 4 + 48;
		
		// Creates my custom 
		if (MinecraftClient.getInstance().isDemo()) {
  
		   if(Consts.background.size() == 0){
			  Consts.warn("Cannot Register new Main menu  because list is 0");
  
		   } else{
			  Consts.log("Registering main menu");
			  overlay.LoadCustomMainMenu(MinecraftClient.getInstance(),this,width, j);
  
			  
			  //overlay.setUpCustomMainMenu(minecraft, this, width,height, j, realmsNotificationsScreen);
			  Consts.log("Registered main menu");
		   }
  
		} else {
  
		   if(Consts.background.size() == 0){
			  Consts.warn("Cannot Register new Main menu  because list is 0");
  
		   } else{
			  Consts.log("Registering main menu");
			  overlay.LoadCustomMainMenu(MinecraftClient.getInstance(),this,width, j);
  
			  
			  //overlay.setUpCustomMainMenu(minecraft, this, width,height, j, realmsNotificationsScreen);
			  Consts.log("Registered main menu");
		   }
		}  

	}
	
	@Inject(at = @At("TAIL"), method = "render()V")
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta,CallbackInfo ci) {
		
  
		
		
		int j = this.width / 2 - 137;
		

		   
		   drawStringWithShadow(matrices, this.textRenderer, "Blackburn 1.18", 2, this.height - 20, 16777215 | 22);
		   
		  
  
		   super.render(matrices, mouseX, mouseY, delta);
		   
  
		}
	 }
  

	
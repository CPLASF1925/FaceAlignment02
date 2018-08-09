package com.bril.facedistinguish.utils;

import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class CameraParametersUtils {
	Camera.Parameters parameters;
	public int srcWidth, srcHeight;
	public int preWidth, preHeight;
	public int picWidth, picHeight;
	public int surfaceWidth, surfaceHeight;
	List<Camera.Size> list;
	private boolean isShowBorder = false;
   
	public CameraParametersUtils(Context context) {
		
		setScreenSize(context);
	}
	/**
	 * 豸
	 * @param camera
	 */
 public void getCameraPicParameters(Camera camera){
	 isShowBorder = false;
	 parameters = camera.getParameters();
	 list = parameters.getSupportedPictureSizes();
	 float ratioScreen = (float) srcWidth / srcHeight;
	 for (int i = 0; i < list.size(); i++) {
			float ratioPicture = (float) list.get(i).width / list.get(i).height;
			if (ratioScreen == ratioPicture) {// ж
				if (list.get(i).width >= 1600 || list.get(i).height >= 1200) {// 1600*1200
					if (picWidth == 0 && picHeight == 0) {// 
						picWidth = list.get(i).width;
						picHeight = list.get(i).height;
					}
					if (list.get(0).width > list.get(list.size() - 1).width) {
						// 
						if (picWidth > list.get(i).width
								|| picHeight > list.get(i).height) {
							// д1600*1200См
							picWidth = list.get(i).width;
							picHeight = list.get(i).height;
						}
					} else {
						// С
						if (picWidth < list.get(i).width
								|| picHeight < list.get(i).height) {
							// 1600*1200
							if (picWidth >= 1600 || picHeight >= 1200) {

							} else {
								// picWidthpicHeightб1600*1200
								picWidth = list.get(i).width;
								picHeight = list.get(i).height;
							}
						}
					}
				}
			}
	 }
	  // 
			if (picWidth == 0 || picHeight == 0) {
				isShowBorder = true;
				picWidth = list.get(0).width;
				picHeight = list.get(0).height;
				for (int i = 0; i < list.size(); i++) {
					
					if (list.get(0).width > list.get(list.size() - 1).width) {
						// 
						if (picWidth >=list.get(i).width
								|| picHeight >= list.get(i).height) {
							// ε:
							if (list.get(i).width >= 1600) {
								// ε1280*720
								picWidth = list.get(i).width;
								picHeight = list.get(i).height;

							}
						}
					} else {
						if (picWidth <= list.get(i).width
								|| picHeight <= list.get(i).height) {
							if (picWidth >= 1600 || picHeight >= 1200) {

							} else {
								// ε:
								if (list.get(i).width >= 1600) {
									// ε1280*720
									picWidth = list.get(i).width;
									picHeight = list.get(i).height;

								}
							}

						}
					}
				}
			}
			// 1280*720е
			if (picWidth == 0 || picHeight == 0) {
				isShowBorder = true;
				if (list.get(0).width > list.get(list.size() - 1).width) {
					picWidth = list.get(0).width;
					picHeight = list.get(0).height;
				} else {
					picWidth = list.get(list.size() - 1).width;
					picHeight = list.get(list.size() - 1).height;
				}
			}
			if (isShowBorder) {
				if (ratioScreen > (float) picWidth / picHeight) {
					float rp=ratioScreen-((float) preWidth / preHeight);
					//0.02
					if(rp<=0.02){
						surfaceWidth = srcWidth;
						surfaceHeight = srcHeight;
					}else{
						surfaceWidth = (int) (((float) preWidth / preHeight) * srcHeight);
						surfaceHeight = srcHeight;
					}
				} else {
					surfaceWidth = srcWidth;
					surfaceHeight = (int) (((float) picWidth / picHeight) * srcHeight);
				}
			}else{
				surfaceWidth = srcWidth;
				surfaceHeight=srcHeight;
			}
			System.out.println("surfaceWidth:"+surfaceWidth+"--surfaceHeight:"+surfaceHeight);
 }
	/**
	 * 豸
	 * 
	 * @param camera
	 */
	public void getCameraPreParameters(Camera camera)

	{
		isShowBorder = false;
		// 豸
		if ("PLK-TL01H".equals(Build.MODEL)) {
			preWidth = 1920;
			preHeight = 1080;
			return;
		}
		
		// 豸
		parameters = camera.getParameters();
		list = parameters.getSupportedPreviewSizes();
		float ratioScreen = (float) srcWidth / srcHeight;

		for (int i = 0; i < list.size(); i++) {
			float ratioPreview = (float) list.get(i).width / list.get(i).height;
			if (ratioScreen == ratioPreview) {// ж
				if (list.get(i).width >= 1280 || list.get(i).height >= 720) {// 1280*720
					if (preWidth == 0 && preHeight == 0) {// 
						preWidth = list.get(i).width;
						preHeight = list.get(i).height;
					}
					if (list.get(0).width > list.get(list.size() - 1).width) {
						if (preWidth > list.get(i).width
								|| preHeight > list.get(i).height) {
							preWidth = list.get(i).width;
							preHeight = list.get(i).height;
						}
					} else {
						if (preWidth < list.get(i).width
								|| preHeight <list.get(i).height) {
							if (preWidth >= 1280 || preHeight >= 720) {

							} else {
								preWidth = list.get(i).width;
								preHeight = list.get(i).height;
							}
						}
					}
				}
			}
		}
		if (preWidth == 0 || preHeight == 0) {
			isShowBorder = true;
			preWidth = list.get(0).width;
			preHeight = list.get(0).height;
			for (int i = 0; i < list.size(); i++) {
				
				if (list.get(0).width > list.get(list.size() - 1).width) {
					if (preWidth >=list.get(i).width
							|| preHeight >=list.get(i).height) {
						// ε:
						if (list.get(i).width >= 1280) {
							// ε1280*720
							preWidth = list.get(i).width;
							preHeight = list.get(i).height;

						}
					}
				} else {
					if (preWidth <= list.get(i).width
							|| preHeight <= list.get(i).height) {
						if (preWidth >= 1280 || preHeight >= 720) {

						} else {
							// ε:
							if (list.get(i).width >= 1280) {
								// ε1280*720
								preWidth = list.get(i).width;
								preHeight = list.get(i).height;

							}
						}

					}
				}
			}
		}
		
		// 1280*720е
		if (preWidth <= 640 || preHeight <=480) {
			isShowBorder = true;
			if (list.get(0).width > list.get(list.size() - 1).width) {
				preWidth = list.get(0).width;
				preHeight = list.get(0).height;
			} else {
				preWidth = list.get(list.size() - 1).width;
				preHeight = list.get(list.size() - 1).height;
			}
		}
		if (isShowBorder) {
			if (ratioScreen > (float) preWidth / preHeight) {
				surfaceWidth = (int) (((float) preWidth / preHeight) * srcHeight);
				surfaceHeight = srcHeight;
			} else {
				surfaceWidth = srcWidth;
				surfaceHeight = (int) (((float) preHeight / preWidth) * srcHeight);
			}
		}else{
			surfaceWidth = srcWidth;
			surfaceHeight=srcHeight;
		}
		
		System.out.println("surfaceWidth1:"+surfaceWidth+"--surfaceHeight1:"+surfaceHeight);
	}

	@SuppressLint("NewApi")
	private void setScreenSize(Context context) {
		int x, y;
		WindowManager wm = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE));
		Display display = wm.getDefaultDisplay();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			Point screenSize = new Point();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
				display.getRealSize(screenSize);
				x = screenSize.x;
				y = screenSize.y;
			} else {
				display.getSize(screenSize);
				x = screenSize.x;
				y = screenSize.y;
			}
		} else {
			x = display.getWidth();
			y = display.getHeight();
		}

		srcWidth = x;
		srcHeight = y;
	}
	/**
     * @param mDecorView{tags} 趨
     * @return ${return_type}    
     * @throws
     * @Title: 
     * @Description: 
     */
    @TargetApi(19)
    public void hiddenVirtualButtons(View mDecorView) {
        if (Build.VERSION.SDK_INT >= 19) {
            mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }
}

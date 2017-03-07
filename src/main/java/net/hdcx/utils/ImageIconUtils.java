package net.hdcx.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kevin on 2017/2/27.
 */
public class ImageIconUtils {

	public static void setImageIcon(Window window, Image image){
		window.setIconImage(image);
	}

	public static void setDefaultImageIcon(Window window){
		window.setIconImage(new ImageIcon("res/images/titleicon.png").getImage());
	}

}

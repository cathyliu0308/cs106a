

import java.util.*;

import acm.graphics.*;

public class ImageAlgorithms implements ImageAlgorithmsInterface {
	
	public void grayscale(GImage source) {
		int[][] pixels = source.getPixelArray();
		//System.out.println(pixels.length);
		for (int m = 0; m < pixels.length; m++) {
			for (int n = 0; n < pixels[0].length; n++) {
				int cur = pixels[m][n];
				int red = GImage.getRed(cur);
				int green = GImage.getGreen(cur);
				int blue = GImage.getBlue(cur);
				int avrg = (red + green + blue) / 3;
				pixels[m][n] = GImage.createRGBPixel(avrg, avrg, avrg);
			}
		}
		source.setPixelArray(pixels);
	}

	public void negative(GImage source) {
		int[][] pixels = source.getPixelArray();
		for (int m = 0; m < pixels.length; m++) {
			for (int n = 0; n < pixels[0].length; n++) {
				int cur = pixels[m][n];
				int red = GImage.getRed(cur);
				int green = GImage.getGreen(cur);
				int blue = GImage.getBlue(cur);
				pixels[m][n] = GImage.createRGBPixel(255 - red, 255 - green, 255 - blue);
			}
		}
		source.setPixelArray(pixels);
	}

	public void rotateLeft(GImage source) {
		int[][] pixels = source.getPixelArray();
		int[][] rotate = new int[pixels[0].length][pixels.length];
		for (int m = 0; m < pixels[0].length; m++) {
			for (int n = 0; n < pixels.length; n++) {
				rotate[m][n] = pixels[n][pixels[0].length-1-m];
			}
		}
		pixels = rotate;	
		source.setPixelArray(pixels);
	}

	public void rotateRight(GImage source) {
		int[][] pixels = source.getPixelArray();
		int[][] rotate = new int[pixels[0].length][pixels.length];
		for (int m = 0; m < pixels[0].length; m++) {
			for (int n = 0; n < pixels.length; n++) {
				rotate[m][n] = pixels[pixels.length-1-n][m];
			}
		}
		pixels = rotate;	
		source.setPixelArray(pixels);
	}
	
	public void translate(GImage source, int dx, int dy) {
		int[][] pixels = source.getPixelArray();
		int[][] translate = new int[pixels.length][pixels[0].length];
		int x; int y;
		for (int m = 0; m < pixels.length; m++) {
			for (int n = 0; n < pixels[0].length; n++) {
				x = m + dx; y = n + dy;
				if (x >= pixels.length) {
					x = x % (pixels.length - 1);
				} else if (x < 0) {
					x = pixels.length + x % pixels.length;
				}
				if (y >= pixels[0].length) {
					y = y % (pixels[0].length - 1);
				} else if (y < 0) {
					y = pixels[0].length + y % pixels[0].length;
				}
				translate[x][y] = pixels[m][n];
			}
		}
		pixels = translate;
		source.setPixelArray(pixels);
	}

	public void blur(GImage source) {
		int[][] pixels = source.getPixelArray();
		int r = pixels.length;
		int c = pixels[0].length;
		int count; int red; int green; int blue;
		int[][] blurPixels = new int[r][c];
		for (int m = 0; m < r; m++) {
			for (int n = 0; n < c; n++) {
				count = 0;
				red = 0; green = 0; blue = 0;
				if (isValid(m-1,n-1,r,c)) {
					count ++;
					red += GImage.getRed(pixels[m-1][n-1]);
					green += GImage.getGreen(pixels[m-1][n-1]);
					blue += GImage.getBlue(pixels[m-1][n-1]);
				}
				if (isValid(m-1,n,r,c)) {
					count ++;
					red += GImage.getRed(pixels[m-1][n]);
					green += GImage.getGreen(pixels[m-1][n]);
					blue += GImage.getBlue(pixels[m-1][n]);
				}
				if (isValid(m-1,n+1,r,c)) {
					count ++;
					red += GImage.getRed(pixels[m-1][n+1]);
					green += GImage.getGreen(pixels[m-1][n+1]);
					blue += GImage.getBlue(pixels[m-1][n+1]);
				}
				if (isValid(m,n-1,r,c)) {
					count ++;
					red += GImage.getRed(pixels[m][n-1]);
					green += GImage.getGreen(pixels[m][n-1]);
					blue += GImage.getBlue(pixels[m][n-1]);
				}
				if (isValid(m,n,r,c)) {
					count ++;
					red += GImage.getRed(pixels[m][n]);
					green += GImage.getGreen(pixels[m][n]);
					blue += GImage.getBlue(pixels[m][n]);
				}
				if (isValid(m,n+1,r,c)) {
					count ++;
					red += GImage.getRed(pixels[m][n+1]);
					green += GImage.getGreen(pixels[m][n+1]);
					blue += GImage.getBlue(pixels[m][n+1]);
				}
				if (isValid(m+1,n-1,r,c)) {
					count ++;
					red += GImage.getRed(pixels[m+1][n-1]);
					green += GImage.getGreen(pixels[m+1][n-1]);
					blue += GImage.getBlue(pixels[m+1][n-1]);
				}
				if (isValid(m+1,n,r,c)) {
					count ++;
					red += GImage.getRed(pixels[m+1][n]);
					green += GImage.getGreen(pixels[m+1][n]);
					blue += GImage.getBlue(pixels[m+1][n]);
				}
				if (isValid(m+1,n+1,r,c)) {
					count ++;
					red += GImage.getRed(pixels[m+1][n+1]);
					green += GImage.getGreen(pixels[m+1][n+1]);
					blue += GImage.getBlue(pixels[m+1][n+1]);
				}
				red = red / count;
				green = green / count;
				blue = blue / count;
				blurPixels[m][n] = GImage.createRGBPixel(red, green, blue);
			}
		}
		pixels = blurPixels;
		source.setPixelArray(pixels);
	}
	
	private boolean isValid(int x, int y, int r, int c) {
		if ((x >= r) || (x < 0) || (y >= c) || (y < 0)) {
			return false;
		} else {
			return true;
		}
	}


    public void mystery(GImage source) {
        // TODO: (optional) have some fun!
    }
}

package togo.plannumerotation;

import android.widget.PopupWindow;
/**
 * 
 * @author aristide mendoo.aristide@gmail.com
 *
 *dismiss popup after a certain amount of time
 */
public class DissmissPopup implements Runnable {
    
	private PopupWindow popup;
	public DissmissPopup(PopupWindow popup) {
		this.popup=popup;
	}
	@Override
	public void run() {
        if(popup!=null){
        	popup.dismiss();
        }    
	}

}

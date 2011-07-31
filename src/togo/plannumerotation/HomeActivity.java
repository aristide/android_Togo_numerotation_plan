package togo.plannumerotation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * 
 * @author aristide Home Activity holding the dashboard
 * 
 **/
public class HomeActivity extends Activity {

	private Button btn_update;
	private Button btn_help_dashbord;
	private ContactsInfos contactslist;
	private Button btn_see_changes;
	private Button btn_others;
	private Button btn_help;
	private PopupWindow popup;
	private PopupWindow popupAide;
	private PopupWindow popupAuteur;
	//private static final int POPU_DISMISS_DELAY = 4000;
	//private DissmissPopup mdismissPopup;
	//private Handler handler;
	private Button ok_aide;
	private Button auteur_ok;
	private int width;
	//private int height;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// screen dimensions
		Display display = getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		//height = display.getHeight();

		// contact list updater
		contactslist = new ContactsInfos(this);
		//handler = new Handler();

		LayoutInflater inflate = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// Popup Views
		View popupAideView = inflate.inflate(R.layout.aide_layout, null);
		View popupAuteurview = inflate.inflate(R.layout.auteur_layout, null);

		// popups
		popupAide = new PopupWindow(popupAideView, 300, 350);
		popupAuteur = new PopupWindow(popupAuteurview, 300, 230);

		//mdismissPopup = new DissmissPopup(popup);

		popupAide.setOutsideTouchable(false);
		popupAide.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});

		popupAuteur.setOutsideTouchable(false);
		popupAuteur.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});

		btn_update = (Button) findViewById(R.id.btn_update);
		btn_help_dashbord = (Button) findViewById(R.id.btn_undo_update);
		btn_see_changes = (Button) findViewById(R.id.btn_see_changes);
		btn_others = (Button) findViewById(R.id.btn_others);
		btn_help = (Button) findViewById(R.id.btn_help);
		ok_aide = (Button) popupAideView.findViewById(R.id.ok_aide);
		auteur_ok = (Button) popupAuteurview.findViewById(R.id.auteur_ok);

		// button to update contact list
		btn_update.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!contactslist.RunUpdate()) {
					Toast toast4 = new Toast(HomeActivity.this);
					toast4 = Toast.makeText(HomeActivity.this, "Rien a Faire",
							Toast.LENGTH_SHORT);
					toast4.setGravity(Gravity.TOP, 0, 20);
					toast4.show();

				} else {
					Toast toast5 = new Toast(HomeActivity.this);
					toast5 = Toast.makeText(HomeActivity.this, contactslist
							.getChanges().size()
							+ getString(R.string.mis_a_jour),
							Toast.LENGTH_SHORT);
					toast5.setGravity(Gravity.TOP, 0, 20);
					toast5.show();
				}
			}
		});

		// display help
		btn_help_dashbord.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				popupAide.showAtLocation(HomeActivity.this
						.findViewById(R.id.main), Gravity.NO_GRAVITY, width
						- (width - 300) / 2 - 300, 70);
			}
		});

		// see update or changes
		btn_see_changes.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!(contactslist.getChanges().size() > 0)) {
					Toast toast3 = new Toast(HomeActivity.this);
					toast3 = Toast.makeText(HomeActivity.this,
							getString(R.string.nochanges), Toast.LENGTH_SHORT);
					toast3.setGravity(Gravity.TOP, 0, 20);
					toast3.show();
				} else {
					Intent i = new Intent(HomeActivity.this,
							ListeModification.class);
					// i.putParcelableArrayListExtra("modifs",
					// contactslist.getModifications());
					// Bundle bundle = new Bundle();
					// bundle.putSerializable("noms",
					// contactslist.getModifications());

					i.putStringArrayListExtra("noms", contactslist.getNom());
					i.putStringArrayListExtra("nouveauNum", contactslist
							.getnouveauNum());
					i.putStringArrayListExtra("ancienNum", contactslist
							.getanciemNum());
					HomeActivity.this.startActivity(i);
				}
			}
		});

		// action bar help
		btn_help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DisplayPopup();
				popupAide.showAtLocation(HomeActivity.this
						.findViewById(R.id.main), Gravity.NO_GRAVITY, width
						- (width - 300) / 2 - 300, 70);
			}
		});

		// display author popup
		btn_others.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				popupAuteur.showAtLocation(HomeActivity.this
						.findViewById(R.id.main), Gravity.NO_GRAVITY, width
						- (width - 300) / 2 - 300, 70);
			}
		});

		// dismiss help popup
		ok_aide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popupAide.dismiss();
			}
		});

		// dismiss author popup
		auteur_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popupAuteur.dismiss();
			}
		});
	}

	// not used in this class class
	public void DisplayPopup() {
		// popup.showAtLocation(this.findViewById(R.id.main),
		// Gravity.NO_GRAVITY,10,10);
		// handler.postDelayed(mdismissPopup, POPU_DISMISS_DELAY);
	}

}
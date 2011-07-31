/**
 * 
 */
package togo.plannumerotation;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author aristide
 * 
 */
public class MonAdapter extends BaseAdapter {
	private ArrayList<Modifications> modifs;
	private LayoutInflater myinflater;
	Viewholder vue;

	/**
	 * 
	 */
	public MonAdapter(Context context, ArrayList<Modifications> _modifs) {
		this.myinflater = LayoutInflater.from(context);
		this.modifs = _modifs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return modifs.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return modifs.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = myinflater.inflate(R.layout.template_item, null);
			vue = new Viewholder();
			vue.textview1 = (TextView) convertView
					.findViewById(R.id.nom_contacts);
			vue.textview2 = (TextView) convertView
					.findViewById(R.id.ancien_contacts);
			vue.textview3 = (TextView) convertView
					.findViewById(R.id.nouveau_contacts);

			convertView.setTag(vue);
		} else {
			vue = (Viewholder) convertView.getTag();
		}
		vue.textview1.setText(modifs.get(position).nom);
		vue.textview2.setText("Ancien Numéro : "
				+ modifs.get(position).ancienNum);
		vue.textview3.setText("Nouveau Numéro : "
				+ modifs.get(position).nouveauNum);
		Log.v("nom", vue.textview1.getText().toString());
		Log.v("ancien", vue.textview2.getText().toString());
		Log.v("nouveau", vue.textview3.getText().toString());

		return convertView;
	}

}

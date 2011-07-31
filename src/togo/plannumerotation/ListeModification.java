package togo.plannumerotation;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author aristide mendoo.aristide@gmail.com
 * j2me/android developper
 * 
 * display list of modifications made in you contact list
 * in form of ListVIew
 */
public class ListeModification extends Activity {
	
	ArrayList<Modifications> modifs;
	private ListView listview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		listview = (ListView) findViewById(R.id.malistview);
		ArrayList<String> noms= (ArrayList<String>)getIntent().getStringArrayListExtra("noms");
		ArrayList<String> ancienNum= (ArrayList<String>)getIntent().getStringArrayListExtra("ancienNum");
		ArrayList<String> nouveauNum= (ArrayList<String>)getIntent().getStringArrayListExtra("nouveauNum");
		modifs=formModif(noms, ancienNum, nouveauNum);	
		
	
		MonAdapter adapter= new MonAdapter(this, modifs); 
		listview.setAdapter(adapter);
        listview.setTextFilterEnabled(true);	
		//to pass :
		//   intent.putExtra("MyClass", obj);  
		// to retrieve object in second Activity
		//getIntent().getSerializableExtra("MyClass");
        
        listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),((TextView)view.findViewById(R.id.nom_contacts)).getText()+" : " +((TextView)view.findViewById(R.id.ancien_contacts)).getText()+" ==> " +((TextView)view.findViewById(R.id.nouveau_contacts)).getText(), Toast.LENGTH_SHORT).show();
				
			}
			
        });
	}
	
	//form an Arralist of modification from intent data 
	public ArrayList<Modifications> formModif(ArrayList<String> noms,ArrayList<String> ancienNum,ArrayList<String> nouveauNum){
		ArrayList<Modifications>  modif = new ArrayList<Modifications>();
		for(int i=0; i<noms.size();i++){
			modif.add(new Modifications(noms.get(i), ancienNum.get(i), nouveauNum.get(i)));
		}
		return modif;
	}
}

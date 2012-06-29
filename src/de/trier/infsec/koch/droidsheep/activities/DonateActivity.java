/*
 * DonateActivity.java pops up a donation reminder 
 * Copyright (C) 2011 Andreas Koch <koch.trier@gmail.com>
 * 
 * This software was supported by the University of Trier
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package de.trier.infsec.koch.droidsheep.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import de.trier.infsec.koch.droidsheep.R;
import de.trier.infsec.koch.droidsheep.helper.DBHelper;

public class DonateActivity extends Activity implements OnClickListener {

	Button bYes = null;
	Button bNo  = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	

	@Override
	protected void onStart() {
		super.onStart();
		setContentView(R.layout.donation);
		bYes = (Button) findViewById(R.id.donate_yes);
		bNo  = (Button) findViewById(R.id.donate_no);
		bYes.setOnClickListener(this);
		bNo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v != null && bYes != null && v.equals(bYes)) {
		    String url = "http://droidsheep.de/?page_id=121";  
		    Intent i = new Intent(Intent.ACTION_VIEW);  
		    i.setData(Uri.parse(url));  
		    startActivity(i);
		    finish();
		} else if (v != null && bNo != null && v.equals(bNo)) {
			DBHelper.setLastDonateMessage(this, System.currentTimeMillis());
			this.finish();
		}
	}
}

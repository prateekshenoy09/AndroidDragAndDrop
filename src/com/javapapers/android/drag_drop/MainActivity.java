package com.javapapers.android.drag_drop;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.View.DragShadowBuilder;
import android.widget.LinearLayout;
import android.util.Log;
import com.javapapers.android.drag_drop.R;


public class MainActivity extends Activity implements OnTouchListener,OnDragListener{
	private static final String LOGCAT = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.textView1).setOnTouchListener(this);
		findViewById(R.id.pinkLayout).setOnDragListener(this);
		findViewById(R.id.yellowLayout).setOnDragListener(this);
	}
	public boolean onTouch(View view, MotionEvent motionEvent) { 
		    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		      DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			  view.startDrag(null, shadowBuilder, view, 0);
			  view.setVisibility(View.INVISIBLE);
			  return true;
		    }
		    else {
		    	return false;
		    }
	}  
    public boolean onDrag(View layoutview, DragEvent dragevent) {
	      int action = dragevent.getAction();
	      switch (action) {
	      case DragEvent.ACTION_DRAG_STARTED:
	          Log.d(LOGCAT, "Drag event started");
	    	break;
	      case DragEvent.ACTION_DRAG_ENTERED:
	    	  Log.d(LOGCAT, "Drag event entered into "+layoutview.toString());
	    	break;
	      case DragEvent.ACTION_DRAG_EXITED:
	    	  Log.d(LOGCAT, "Drag event exited from "+layoutview.toString());
	    	break;
	      case DragEvent.ACTION_DROP:
	    	Log.d(LOGCAT, "Dropped");
	    	View view = (View) dragevent.getLocalState();
	        ViewGroup owner = (ViewGroup) view.getParent();
	        owner.removeView(view);
	        LinearLayout container = (LinearLayout) layoutview;
	        container.addView(view);
	        view.setVisibility(View.VISIBLE);
	        break;
	      case DragEvent.ACTION_DRAG_ENDED:
	    		  Log.d(LOGCAT, "Drag ended");
		      break;
	      default:
	        break;
	      }
	      return true;
    }
}

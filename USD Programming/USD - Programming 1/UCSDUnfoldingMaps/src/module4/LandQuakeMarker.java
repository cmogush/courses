package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PGraphics;

/** Implements a visual marker for land earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class LandQuakeMarker extends EarthquakeMarker {
	
	float mag = (float) 0.0;
	
	public LandQuakeMarker(PointFeature quake) {
		
		// calling EarthquakeMarker constructor
		super(quake);
				
		// setting field in earthquake marker
		isOnLand = true;
	}
 

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		//get size of magnitude 
		// Draw a centered circle for land quakes
		// DO NOT set the fill color here.  That will be set in the EarthquakeMarker
		// class to indicate the depth of the earthquake.
		// Simply draw a centered circle.
		
		// HINT: Notice the radius variable in the EarthquakeMarker class
		// and how it is set in the EarthquakeMarker constructor
		
		// TODO: Implement this method
		// Restore previous drawing style
		pg.pushStyle();
		float width = radius*2;
		float height = radius*2;
		pg.ellipse(x,y,width,height);
		if(recent) {
			pg.line(x-radius, y-radius, x+radius, y+radius);
			pg.line(x+radius, y-radius, x-radius, y+radius);
		}
		pg.popStyle();//end draw
		
	}
	

	// Get the country the earthquake is in
	public String getCountry() {
		return (String) getProperty("country");
	}



		
}
// srd/view/grid.js
////////////////////////////////
// grid VIEW CLASS
//
//
//
//
//
/////////////////////////////////
define( 'srd/view/grid', [
	"dojo/_base/declare",
	"dojo/on",
	"dojo/_base/lang",
	"dojo/dnd/Target",
	"dojo/aspect",
	"dgrid/Grid",
	"srd/view"
], function(declare, on, lang, Target, aspect, Grid, view) {


//srd_view_map class definition using dojo.declare 
	return declare( 
	'grid',
	[view],
	{
		type: 'grid',
		// grid VARIABLES 
		grid: null,

		//CONSTUCTOR
		constructor : function( data, parent_srd_doc) {
			console.log("srd/view/grid constructor called !");
			this.start_lat = this.data.start_lat;
			
			this.contentPane = new dijit.layout.ContentPane(
					{  
		           		content: "<div id=divGrid></div>",
						region: this.data.region
					} );				
				this.srd_doc.srd_container.addChild(this.contentPane);
				this.srd_doc.srd_container.resize();

/*				var columns = {
			        first: {
			            label: "First Name"
			        },
			        last: {
			            label: "Last Name"
			        }
			    };
			    this.grid = new Grid({ columns: columns }, "divGrid" ); // attach to a DOM id
			    this.grid.renderArray(arrayOfData); // render some data
*/
				this.grid = new Grid( {} , "divGrid");
			
			
		}
		// END CONSTRUCTOR
	
	
	
	
	} );
	//END declare
} );
//END grid CLASS
			
			
			
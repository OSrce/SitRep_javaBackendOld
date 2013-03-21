



define( [
//	"srd/dojo_bootloader",
	"dojo/_base/declare",
	"srd/srd_rtc",
	"srd/srd_layer",
	"srd/view/menubar",
	"srd/view/layertree",
	"srd/view/map",
	"srd/srd_view",
	"srd/srd_gridContainer",
	"srd/dashboard",
	"srd/srd_view_map",
	"srd/srd_view_cfssingle",
	"srd/srd_view_admin",
	"srd/srd_view_datagrid",
	"srd/srd_view_opstrack",
	"dojo",
	"dojox/data/QueryReadStore",
//	"dojox/storage/LocalStorageProvider",
	"dijit/layout/BorderContainer",
	"dijit/layout/ContentPane",
	"dijit/Menu",
	"dijit/MenuBar",
	"dijit/MenuBarItem",
	"dijit/PopupMenuBarItem",
	"dijit/form/ComboBox",
	"dojo/domReady!"
//] , function( doc_include,  declare, srd_rtc, srd_layer, srd_view, srd_gridContainer  ) {
] , function( declare, srd_rtc, srd_layer, view_menubar, view_layertree, view_map, srd_view, srd_gridContainer, dashboard  ) {
//dashboard_desktop CLASS 
return declare( 'desktop', [dashboard], {

	constructor : function( loadsrd ) {
	console.log( "dashboard_desktop Constructor Called!");	
		
	
	
	if(this.srd_container == null) {
		var jsDisabled = dojo.byId("jsDisabled");
		dojo.style(jsDisabled, "display", "none");
		this.srd_container = new dijit.layout.BorderContainer( { 
				gutters: false,
				splitter: "false",
				design: "headline"
			 }, 'theSrdDoc' );
		this.srd_container.startup();
	}


//this.srd_displayMenuBar();


console.log("test2");
// ASSUME view_layer_x/y have been set and that settings are populated
// parse and init different views
/*
this.centerPane = new dijit.layout.ContentPane( { 
	region: 'center',
	id: 'srd_centerPane'
} );
this.srd_container.addChild(this.centerPane);
*/

console.log("test");

//this.srd_changeWindowLayout(this.staticVals.default_wlayout);
var menubar_data = {
		region: "top"
	};
this.view_menubar = new view_menubar(menubar_data, this);


var layertree_data = {
	theGroups: loadsrd.theGroups,
	region: "left"
};
this.view_layertree = new view_layertree( layertree_data, this);

var map_data = {
		//TODO SHOULD NOT BE HARDCODED
		start_lat : 40.713,
		start_lon : -73.996,
		start_zoom : 12,
		start_base_layer: 1002,
	region: "center"
};
this.view_map = new view_map(map_data, this);



//TODO PUT BACK IN FOR COMETD STUFF.
//this.rtc = new srd_rtc(this);
	
	
	
	
		
	}


//END DECLARE
} );
//END DEFINE
});
		
		

/*
if(dojo.isIE) {
	"dojox.form.uploader.plugins.IFrame");
} else if(dojo.isKhtml) {
	"dojox.form.uploader.plugins.HTML5");
} else {
	"dojox.form.uploader.plugins.Flash");
}
*/

define( [
//	"srd/dojo_bootloader",
	"dojo/_base/declare",
	"srd/srd_rtc",
	"srd/srd_layer",
	"srd/view_layertree",
	"srd/srd_view",
	"srd/srd_gridContainer",
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
] , function( declare, srd_rtc, srd_layer, view_layertree, srd_view, srd_gridContainer  ) {

/*dojo.provide("ComboBoxReadStore");
dojo.declare(
	"ComboBoxReadStore",
	dojox.data.QueryReadStore,
	{
		fetch:function(request) {
			request.serverQuery = {q:request.query.name};
			return this.inherited("fetch", arguments);
		}
	}
);
*/


//srd_document CLASS 
return declare( null, {

	constructor : function( loadsrd ) {
	//THE UI VARS
	this.srd_container = null;
	this.srd_menuBar = null;
	this.srd_saveMenu = null;

//	this.srd_mapContent = null;
//	this.srd_adminContent = null;
//	this.srd_dataContent = null;

	this.srd_layerEditMenu = null;
	this.srd_layerEditMenuDropDown = null;
	
	this.fileSelDialog = null; 
	this.srd_uploader = null;
	this.oFSubmit = null;
	this.srd_fileList = null;
	this.openFileForm = null;

	//THE OpenLayers VARS
//	this.map = null;
	this.editTools = null;
	this.selectControl = null;
	this.drawControls = null;
	this.srd_panel = null;
	this.theSelectedControl = null;

	this.srd_toolbar = null;
	
	this.selected_wlayout= null;
	this.srd_wlayoutArr = [];
	this.srd_styleArr = [];
	this.srd_layerArr = [];
	this.srd_presetArr = [];
	this.srd_queryArr = [];

	this.srd_selLayer = null;
	this.srd_selControl = null;

	// LAYER EDIT CONTROLS
	this.srd_drawControls = {
		point: null,
		line: null,
		polygon: null,
		remove: null,
		select: null
	};


// SETTINGS WE SHOULD GET FROM localStore or srd_settings.xml
	this.srd_localStore = null;
	this.srd_xmlStore = null;
		
	this.srd_doc_id = null;
	this.staticVals = { 
			docCount: null,
			single_user: null,
			runFromServer: null,
//			default_projection: null,
//			start_lat: null,
//			start_lon: null,
//			start_zoom: null,

			view_layout_x: null,
			view_layout_y: null,
			view_data: null,
			
			default_wlayout:null,
			// autoRefresh -> should the whole system poll for changes to the tables?
			// right now only used for existing dynamic data (srjson) layers.
			autoRefresh : null,

			layerCount: null
	};
	// srd_themeArr == Array of objects about available css themes.

	this.srd_themeArr = [
		{ 'name': 'Dark on Light Theme', 'classType':'tundra' },
		{ 'name': 'Light on Dark Theme', 'classType':'SitRep_Dark' }
	];

	// srd_document should have a list of sub-objects - srd_view
	// maps, datagrids (spreadsheets), admin, empty, video, etc all should be 
	// sub-classes of srd_view. srd_view's described in staticVals.view_data.
	this.viewContainer = null; 
	this.viewArr = null; 
	this.selectedView = null; 

	this.viewType =  {
		empty 		: 'srd_view',
		map 			: 'srd_view_map',
		datagrid 	: 'srd_view_datagrid',
		admin			:	'srd_view_admin',
		opstrack	:	'srd_view_opstrack',
		cfssingle	:	'srd_view_cfssingle',
		video			:	'srd_view_video'
	};

	this.viewDefaults = {
		empty : {
			type : 'empty'
		},
		map 	: {
			type : 'map',
			start_lat : 40.713,
			start_lon : -73.996,
			start_zoom : 12
		}
	};
	
	//REAL TIME CODE RELATED :
	this.status = 'OFFLINE';

// BEGIN ASSOCIATE DATA FROM SERVER
	this.staticVals = loadsrd.staticVals;
	this.srd_queryArr = loadsrd.srd_queryArr;
	this.srd_wlayoutArr = loadsrd.srd_wlayoutArr;
	this.srd_presetArr = loadsrd.srd_presetArr;
	this.siteLeftImage= loadsrd.siteLeftImage;
	this.siteRightImage= loadsrd.siteRightImage;
	this.siteTitle = loadsrd.title;

	this.loadsrd = loadsrd;
	this.serverBaseUrl = "";
	if( loadsrd.serverBaseUrl) {
		this.serverBaseUrl = loadsrd.serverBaseUrl;
	}
// END ASSOCIATE DATA FROM SERVER



},
// END srd_document CLASS DEF

// SRD_DOCUMENT CONSTRUCTOR
srd_init : function() {
// srd_init : called by SitRepDashboard when we are ready to 
// load initial values and the first 'screen' we will see :
// map, admin, or data.

	console.log("Called srd_init!");
	if(this.staticVals.runFromServer == null ) {
		// LOCAL STORAGE LOADING
		// LOAD THE VALUES AND CHECK TO SEE THAT WE HAVE EVERYTHING
		// WE NEED.  OTHERWISE, LOAD DEFAULTS FROM XML FILE.
/*		this.srd_localStore = new dojox.storage.LocalStorageProvider({});
		if( this.srd_localStore.isAvailable() ) {
			this.srd_localStore.initialize();
			if(this.srd_localStore.initialized == false ) {
				console.log("Store is not initialed yet");
				dojo.event.connect(dojox.storage.manager,
 	                    "loaded", this.srd_localStore,
 	                    this.storeIsInitialized);
			} else {
				console.log("Store Is Inited");
				this.loadFromLocalStore();
			}

		}
*/
	} else if(this.staticVals.runFromServer == true) {
//		console.log("this.staticVals.start_lat == "+this.staticVals.start_lat); 
		this.staticVals.docCount = 1;
	}

	/// TESTING UGLY JUST TO MAKE SURE selLayer isn't NULL.	
	for(var key in this.srd_layerArr) {
		this.srd_selLayer = this.srd_layerArr[key];
	}

	// TESTING - TODO: CLEAN UP - STYLES AND LAYERS (Settings) SHOULD be stores!
/*
	var tmpStore = new dojo.store.Memory({data: theLayers, idProperty: "id"}); 
	tmpStore.query().forEach(function(layerOptions) {
		this.srd_layerArr[layerOptions.id] = new srd_layer();
		this.srd_layerArr[layerOptions.id].options = layerOptions;
		this.srd_layerArr[layerOptions.id].srd_styleArr = this.srd_styleArr;
	}.bind(this) );

	this.srd_layerStore = new dojo.store.Cache(
		dojo.store.JsonRest({
			target: "/srdata/layers/"
		} ),
		tmpStore
	);
*/

	var theLayers = this.loadsrd.theLayers;
	var theStyles = this.loadsrd.theStyles;
//	var theStyleSymbolizers = this.loadsrd.theStyleSymbolizers;
	var theStyleSymbolizers = {};
	for (theId in this.loadsrd.theStyleSymbolizers) {
		console.log("theId:"+theId+"  ID:"+this.loadsrd.theStyleSymbolizers[theId]["id"] );
		theStyleSymbolizers[this.loadsrd.theStyleSymbolizers[theId]["id"] ] = this.loadsrd.theStyleSymbolizers[theId];
	}
	
	var theStyleRules = this.loadsrd.theStyleRules;


	var theStyleMaps = {};
	for( var theId in theStyles) {
		var theStyle = theStyles[theId];
		if(theStyleMaps[theStyle.stylemapId] == null ) {
				theStyleMaps[theStyle.stylemapId] = {};
		}
		var theOptions = {};
		if(theStyle.renderType == 'default') {
			theOptions['isDefault'] = true;
		}
		for(var theRId in theStyleRules) {
//			console.log("Iterating through Rules, Iter:"+theRId+" theId="+theId);
			var tmpRule = theStyleRules[theRId];
			if( tmpRule["styleId"] == theStyles[theId]['stylemapId'] ) {
				var filterData = dojo.fromJson( tmpRule["filterData"] );
				var theFilter = null;
				if(filterData == null) {
					filterData = {};
				}
				switch(String(filterData.type)) {
					case "==" :						
						theFilter = new OpenLayers.Filter.Function( {
							evaluate: function(feature) {
								try{
									return eval( "feature.attributes." + filterData.property) == filterData.value;
								} catch(e) {
									return false;	
								}
							}
						});
/*						theFilter = new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: filterData.property,
							value: filterData.value
						} );
*/
					break;
					case "<" :
						// SHOULD USE OpenLayers.String.format??? HERE!?
//					    var keys = filterData.property.split('.');
						theFilter = new OpenLayers.Filter.Function( {
							property: filterData.property,
							value: filterData.value,
							evaluate: function(attributes) {
//								console.log("Performing Comparison <");
//								console.dir(attributes);
//								console.log("property==="+this.property);
							    var keys = this.property.split('.');
//								console.dir(keys);
								var result = attributes;
								while (keys.length > 0) {
									var key = keys.shift();
									if (typeof result[key] !== 'undefined') {
										result = result[key];
									} else {
										return false;
//										result = 'undefined';
//										break;
									}
								}		
//								console.log("RESULT ==== "+result+" ==="+this.value);
								return (result < this.value);
							}
						});						
/*						
						theFilter = new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.LESS_THAN,
							property: filterData.property,
							value: filterData.value
						} );
*/
					break;
					case ">" :
						theFilter = new OpenLayers.Filter.Function( {
							property: filterData.property,
							value: filterData.value,
							evaluate: function(attributes) {
							    var keys = this.property.split('.');
								var result = attributes;
								while (keys.length > 0) {
									var key = keys.shift();
									if (typeof result[key] !== 'undefined') {
										result = result[key];
									} else {
										return false;
//										result = 'undefined';
//										break;
									}
								}								
								return (result > this.value);
							}
						});

						/*
						theFilter = new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.GREATER_THAN,
							property: filterData.property,
							value: filterData.value
						} );
						*/
					break;
					case "><" :
						theFilter = new OpenLayers.Filter.Function( {
							property: filterData.property,
							lowerBoundary: filterData.lowerBoundary,
							upperBoundary: filterData.upperBoundary,
							evaluate: function(attributes) {
							    var keys = this.property.split('.');
								var result = attributes;
								while (keys.length > 0) {
									var key = keys.shift();
									if (typeof result[key] !== 'undefined') {
										result = result[key];
									} else {
										return false;
										//result = 'undefined';
//										break;
									}
								}								
								return (this.lowerBoundary < result && result < this.upperBoundary);
							}
						});
/*						
						theFilter = new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.BETWEEN,
							lowerBoundary: filterData.lowerBoundary,
							upperBoundary: filterData.upperBoundary,
							value: filterData.value
						} );
*/						
					break;
					default :
/*						theFilter = new OpenLayers.Filter.Comparison({
							type: OpenLayers.Filter.Comparison.EQUAL_TO,
							property: "srstyle",
							value: tmpRule["symbolizer_id"]
						} );
*/
					break;	
				}

				if( theOptions['rules'] == null) {
					theOptions['rules'] = [];
					theOptions['rules'].push(new OpenLayers.Rule({ elseFilter: true }) );
				}
				if( theFilter != null && theStyleSymbolizers[tmpRule["symbolizerId"]] != null ) {
					theOptions['rules'].push(new OpenLayers.Rule({ filter: theFilter, symbolizer: theStyleSymbolizers[tmpRule["symbolizerId"]], elseFilter: tmpRule["elseFilter"] }) );
				} else if( theFilter != null) {
					theOptions['rules'].push(new OpenLayers.Rule({ symbolizer: theStyleSymbolizers[tmpRule["symbolizerId"]], elseFilter: tmpRule["elseFilter"] }) );
				}	
			}
		}
		if(theStyle.stylemapId == 2013) {
			console.log("jonTest3 === theOptions***************");
			jonTest3 = theOptions;
		}
		
		theOptions.defaultsPerSymbolizer = false;	

		theStyleMaps[theStyle.stylemapId][theStyle.renderType] = new OpenLayers.Style( theStyleSymbolizers[theStyle.defaultsymbolizerId] , theOptions);

	}	
	for ( var theId in theStyleMaps) {
		this.srd_styleArr[theId] = new OpenLayers.StyleMap( theStyleMaps[theId] );
	}
	
	// POPULATING THE srd_layerArr from the data that came over the server
	for( var theId in theLayers) {
		var layerOptions = theLayers[theId];
		this.srd_layerArr[layerOptions.id] = new srd_layer();
		this.srd_layerArr[layerOptions.id].options = layerOptions;
//		this.srd_layerArr[layerOptions.id].srd_styleArr = this.srd_styleArr;
		this.srd_layerArr[layerOptions.id].srd_styleMap = this.srd_styleArr[layerOptions.id];
	}	


//	this.srd_container.startup();

//	dojo.addOnLoad(function() {
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
//	}.bind(this) );

//	dojo.addOnLoad(function() {

	this.srd_displayMenuBar();


	console.log("test2");
	// ASSUME view_layer_x/y have been set and that settings are populated
	// parse and init different views
	this.centerPane = new dijit.layout.ContentPane( { 
		region: 'center',
		id: 'srd_centerPane'
	} );
	this.srd_container.addChild(this.centerPane);

	console.log("test");

	this.srd_changeWindowLayout(this.staticVals.default_wlayout);

//	}.bind(this) );

//	this.srd_displayMenuBar();
	
//	this.view_layertree = new view_layertree( this.loadsrd.theGroups, this);
	
	
	//TODO PUT BACK IN FOR COMETD STUFF.
//	this.rtc = new srd_rtc(this);
},
// END srd_init

 
// BEGIN: LOAD FROM LOCAL STORE
loadFromLocalStore : function() {
	//BEGIN CLEAR STORE LINE - DEBUG ONLY
	this.srd_localStore.clear("srd");	
	//END CLEAR STORE LINE - DEBUG ONLY
	var tmpStaticVals = this.srd_localStore.get("staticVals","srd"); 
	for(var tmpVal in tmpStaticVals) {
		this.setValue(tmpVal,tmpStaticVals[tmpVal] );
//		console.log("Loading Values: "+tmpVal+", "+tmpStaticVals[tmpVal]);
	}
	for(var i=1;i<this.staticVals.layerCount; i++) {
		this.srd_layerArr[i] = new srd_layer();
		this.srd_layerArr[i].copyValuesFromLayer( this.srd_localStore.get(i,"srdLayer") );
//		console.log("Loading Layer Settings from LocalStorage:"+this.srd_layerArr[i].name);
	}
	return 0;
},

// END: LOAD FROM LOCAL STORE



storePutHandler : function() {
//	console.log("LocalStorage Put called!");
},


loadDefaults : function() {
	if( this.srd_xmlStore == null ) {
		// READ the srd_settings.xml file and load it into a dojo.data object.
		this.srd_xmlStore = new dojox.data.XmlStore({ 
			url: 'srd_settings.xml',
			label: 'tag_name' 
		});
	} 
	//Fetch all global settings (except layer stuff).
	this.srd_xmlStore.fetch({
		onComplete: function(items,request) { 
			this.defaultSettingsLoaded(items,request);

		}.bind(this),
		onError: function(errScope) {
			this.errorOnLoad(errScope);
		}.bind(this)
	});
},


setValue : function(varName, varValue) {
	switch(String(varName) ) {
		case "single_user" :
		case "runFromServer" :
			if(String(varValue).toUpperCase() == "TRUE") {
				this.staticVals[varName] = Boolean(true);
			} else {
				this.staticVals[varName] = Boolean(false);
			}
			break;
		case "docCount" :
		case "layerCount" :
		case "start_lat" :
		case "start_lon" :
		case "start_zoom" :
			this.staticVals[varName] = Number(varValue);
			break;
		default :
			this.staticVals[varName] = String(varValue);
	}
	return 0;
},


errorOnLoad : function(errorMessage) {
	alert("Error Loading srd_setttings.xml : "+errorMessage);
	
},

defaultSettingsLoaded : function(items,request) {
	this.srd_items = items;
	for(var i=0;i<this.srd_items.length;i++) {
//		var itemName = this.srd_xmlStore.getIdentity( this.srd_items[i] ); 
//		var itemValue = this.srd_xmlStore.getAttributes( this.srd_items[i] ); 
		var itemName = this.srd_xmlStore.getValue( this.srd_items[i], "tagName" ); 
		var itemValue = this.srd_xmlStore.getValue( this.srd_items[i], "text()" ); 
		if( itemName == "layers") {
//			console.log("Layers from="+itemName+"==="+itemValue);
			var item = this.srd_items[i];
//			var theLayerItems = this.srd_xmlStore.getValues(itemValue,"tagName");
//			console.log("theLayerItems.length=",theLayerItems.length);
//			var theItemAtts = this.srd_xmlStore.getAttributes(item);
//			for(var j = 0; j < theItemAtts.length; j++){
//				var values = this.srd_xmlStore.getValues(item, theItemAtts[j]);

			// THE LINE BELOW GETS AN ARRAY OF ALL LAYERS FROM XML.
			var theLayerItemArr = this.srd_xmlStore.getValues(item, "layer");
			// FOR EACH <layer> in <layers>
			for(var j = 0; j < theLayerItemArr.length; j++){
				var theLayerItem = theLayerItemArr[j];
				if(this.srd_xmlStore.isItem(theLayerItem)){
//					console.log("Located a child item with name: [" + this.srd_xmlStore.getValue(theLayerItem,"tagName") + "]");
					var tmpLayerAtts = this.srd_xmlStore.getAttributes(theLayerItem);
					var tmpSrdLayer = new srd_layer();
//			 	CODE TO iterate through the layer variables and assign the values.
					// FOR EACH <layer attribute> in <layer>
					for(var k=0;k < tmpLayerAtts.length;k++) {
						if( tmpLayerAtts[k] in tmpSrdLayer.options) {
//							console.log(":::: Atts="+tmpLayerAtts[k]+":::"+this.srd_xmlStore.getValues(theLayerItem,tmpLayerAtts[k])+":::");
								tmpSrdLayer.setValue( [tmpLayerAtts[k]],  this.srd_xmlStore.getValue(theLayerItem,tmpLayerAtts[k] ) );
							//TIME TO IMPORT StyleMap data into srd_layer
						} else if (tmpLayerAtts[k] == "StyleMap") {
//							console.log("STYLEMAP="+tmpLayerAtts[k]+":::"+this.srd_xmlStore.getValues(theLayerItem,tmpLayerAtts[k])+":::");
							var theStyleMap = this.srd_xmlStore.getValue(theLayerItem,"StyleMap");
							var theStyleArr = this.srd_xmlStore.getValues(theStyleMap,"Style");
							// FOR EACH <Style> in <StyleMap>
							for(var l=0; l < theStyleArr.length; l++) {
								var theStyleItem = theStyleArr[l];
//								console.log("StyleItem======="+theStyleItem[l]);
								if(this.srd_xmlStore.isItem(theStyleItem) ) {
									var theStyleAtts = this.srd_xmlStore.getAttributes(theStyleItem);
//									console.log("StyleAtts======="+theStyleAtts);
									var styleName = this.srd_xmlStore.getValues(theStyleItem,"name");
									tmpSrdLayer.createStyle(styleName);
									for(var m=0; m < theStyleAtts.length; m++) {
										tmpSrdLayer.setStyleProperty(styleName,theStyleAtts[m],this.srd_xmlStore.getValue(theStyleItem,theStyleAtts[m]) );
									}
								}
							}
						}										
					}
					this.srd_layerArr[tmpSrdLayer.options.id] = tmpSrdLayer;
					this.srd_localStore.put(tmpSrdLayer.options.id,tmpSrdLayer,this.storePutHandler,"srdLayer");
					tmpSrdLayer = null;
				}
			}	
		} else {
//			alert("Layer="+i+", itemName="+itemName+", itemValue="+itemValue+"===");
			//THIS NIFTY LINE STORES THE VALUES FROM THE XML TO THE srd_document variables.
			this.setValue(itemName, itemValue);

		}
	}

		this.staticVals.layerCount = this.srd_layerArr.length;
	
		// TODO : immplement docCount so that srd can keep track of all
		// windows/pages that are open so that you can view multiple maps/data/prefs pages
		// with uniform presentation.
		if( this.staticVals.docCount == null) {
			this.staticVals.docCount = 1;
		} else {
			this.staticVals.docCount++;
		}		
		console.log("Putting staticVals in localstore!");
		this.srd_localStore.put("staticVals", this.staticVals,this.storePutHandler,"srd");

//		this.map_init();
},

srd_createWhiteboard : function() {
	var theDate = new Date();
	var theFrmt = "yyyyMMdd_HHmm";
	var name = "WhiteBoard_"+dojo.date.locale.format(theDate,  {
            selector: "date",
            datePattern: theFrmt
        });
	var url ="/srdata/features/";
	this.srd_createLayer(name,url);
},
	
srd_createLayer : function(theName,theUrl) {
//	var tmpLayer = new srd_layer();
	var tmpOptions = {};
	tmpOptions.name = theName;
	if(theUrl != "") {
		tmpOptions.url = theUrl;
	}
	tmpOptions.type = "Vector";
					tmpOptions.format = "SRJSON";

				//	TODO : FIX THESE!!!
				//	tmpOptions.projection = this.staticVals.default_projection;
				//		tmpOptions.isBaseLayer = false;
						tmpOptions.visibility = true;
						dojo.when( this.srd_layerStore.add(tmpOptions), function( returnId ) {
						console.log("Create Layer Called and New Layeroptions object returned! ID:"+returnId);
						this.id = returnId;
						this.isBaseLayer = false;
						this.can_update = true;
						this.can_delete = true;
						this.feature_create = true;
						this.feature_update = true;
						this.feature_delete = true;
						console.log("New Layer Object Returned! Name="+this.name);

						srd.srd_layerArr[this.id] = new srd_layer();
						srd.srd_layerArr[this.id].options = this;
						srd.staticVals.layerCount++;

						srd.srd_layerArr[this.id].loadData();
						srd.srd_layerArr[this.id].addLayerToMap(srd.selectedView.map);

				//	this.srd_selLayer = tmpLayer;
						srd.srd_saveMenu.addChild(new dijit.MenuItem( { 
								label: this.name,
								onClick: function() { srd.saveLayer(returnId); }.bind(srd)
						} ) );
						if(srd.srd_layerEditMenu != null) {
							srd.srd_layerEditMenu.addChild(new dijit.MenuItem( { 
									label: this.name,
									onClick: function() { srd.srd_selectEditLayer( returnId );  }.bind(srd)
							} ) );
						}
					}.bind(tmpOptions)  );

},
// END srd_createLayer

//BEGIN srd_displayMenuBar
				srd_displayMenuBar : function() {
					console.log("Adding Menu Bar");
//					dojo.addOnLoad(function() {
						if( this.srd_menuBar == null) {
							this.srd_menuBar = new dijit.MenuBar( { 
								splitter: false,
								'region': 'top',
								style: "margin:0px;padding:0px;"
							} );
							//// ICON in LEFT CORNER ////
							this.srd_menuBar.addChild(new dijit.MenuBarItem( {
								label: '<img src="'+this.serverBaseUrl+'/lib/img/SitRepIcon_Small.png" height="20" width="20">' } ) );
							//// SitRep MENU /////	
//							var srd_sitrepMenu = new dijit.Menu({});
							this.srd_menuBar.addChild(new dijit.MenuBarItem({
								label: "SitRep" 
							} ) );

							//// File Menu ////
							var srd_fileMenu = new dijit.Menu({});
							this.srd_menuBar.addChild(new dijit.PopupMenuBarItem({
								label: "File",
								popup: srd_fileMenu
							}) );

							srd_fileMenu.addChild(new dijit.MenuItem({
								label: "New Whiteboard Layer",
								srd: this,
								onClick: function() { srd.srd_createWhiteboard();  }
							}));

							srd_fileMenu.addChild(new dijit.MenuItem({
								label: "Open",
								srd: this,
								onClick: function() { srd.openFile(); }
							}));
							srd_fileMenu.addChild(new dijit.MenuItem({
								label: "Save Project",
								onClick: function() { alert("Future Function - Save Project"); }
							}));
							this.srd_saveMenu = new dijit.Menu( );
							for( tmpId in this.srd_layerArr) {
								if(this.srd_layerArr[tmpId].options.type == "Vector" && this.srd_layerArr[tmpId].feature_update == true) {
									this.srd_saveMenu.addChild(new dijit.MenuItem( { 
										label: this.srd_layerArr[tmpId].name,
										srd: this,
										onClick: function() { srd.saveLayer(tmpId); }
									} ) );
								}
							}	

							srd_fileMenu.addChild(new dijit.PopupMenuItem({
								label: "Save Layer",
								popup:this.srd_saveMenu
							}));

							//// Edit Menu ////
							var srd_editMenu = new dijit.Menu({});
							this.srd_menuBar.addChild(new dijit.PopupMenuBarItem({
								label: "Edit",
								popup: srd_editMenu
							}) );
							srd_editMenu.addChild(new dijit.MenuItem({
								label: "TEST1",
								onClick: function() { alert("Place TEST Here"); }
							}));
							//// View Menu ////
							this.srd_viewMenu = new dijit.Menu({});
							this.srd_menuBar.addChild(new dijit.PopupMenuBarItem({
								label: "View",
								popup: this.srd_viewMenu
							}) );
							var theLabel = "Selected View : ";
							if(this.selectedView) {
								theLabel = theLabel+this.selectedView.data.type+" "+this.selectedView.data.xPos+","+this.selectedView.data.yPos;
							}
							this.srd_viewMenuSelected = new dijit.MenuItem({
								label: theLabel
							});
							this.srd_viewMenu.addChild(this.srd_viewMenuSelected);
							// VIEW TYPE MENU DROP DOWN
							this.srd_viewTypeMenu = new dijit.Menu();
							for(var theType in this.viewType) {
								this.srd_viewTypeMenu.addChild( new dijit.MenuItem( {
									label: theType,
									value: theType,
									srd_doc: this,
									onClick: function() { this.srd_doc.srd_changeViewType(this.value); }
								} ) );
							}
							this.srd_viewMenu.addChild(new dijit.PopupMenuItem({
								label: "Change Type: ",
								popup:this.srd_viewTypeMenu
							}));

							// ROW SIZE MENU DROP DOWN
							this.srd_windowRowMenu = new dijit.Menu();
							for(var y=1;y<5;y++) {
								this.srd_windowRowMenu.addChild( new dijit.MenuItem( {
									label: y,
									value: y,
									srd_doc: this,
									onClick: function() { this.srd_doc.srd_changeViewGridDimensions('y',this.value); }
								} ) );
							}
							this.srd_viewMenu.addChild(new dijit.PopupMenuItem({
								label: "View Rows: "+this.staticVals.view_layout_y,
								popup:this.srd_windowRowMenu	
							}));

							// COLUMN SIZE MENU DROP DOWN
							this.srd_windowColMenu = new dijit.Menu();
							for(var x=1;x<5;x++) {
								this.srd_windowColMenu.addChild( new dijit.MenuItem( {
									label: x,
									value: x,
									srd_doc: this,
									onClick: function() { this.srd_doc.srd_changeViewGridDimensions('x',this.value); }
								} ) );
							}
							this.srd_viewMenu.addChild(new dijit.PopupMenuItem({
								label: "View Columns: "+this.staticVals.view_layout_x,
								popup:this.srd_windowColMenu	
							}));

							// CHANGE THEME MENU DROP DOWN
							this.srd_themeMenu = new dijit.Menu();
							for(var themeId in this.srd_themeArr) {
								this.srd_themeMenu.addChild( new dijit.MenuItem( {
									label: this.srd_themeArr[themeId].name,
									value: themeId,
									srd_doc: this,
									onClick: function() { this.srd_doc.srd_changeTheme(this.value); }
								} ) );
							}

							this.srd_viewMenu.addChild(new dijit.PopupMenuItem({
								label: "Change Theme : ",
								popup:this.srd_themeMenu	
							}));
							///// END View Menu ////	

							this.srd_dataMenuFiller = new dijit.Menu();
							//// Data Menu ////
							this.srd_dataMenuPopup = new dijit.PopupMenuBarItem({
								label: "Data",
								popup: this.srd_dataMenuFiller
//								popup: this.selectedView.dataMenu
							} );
							this.srd_menuBar.addChild(this.srd_dataMenuPopup);

							/// END Data Menu
							this.srd_dataMenu = this.srd_viewMenu;	


							//// Tools Menu ////
							var srd_toolsMenu = new dijit.Menu({});
							this.srd_menuBar.addChild(new dijit.PopupMenuBarItem({
								label: "Tools",
								popup: srd_toolsMenu
							}) );
							// Toggle for Edit Toolbar
							srd_toolsMenu.addChild(
								new dijit.CheckedMenuItem({
									label: "Edit Toolbar",
									srd_doc: this,
									onClick: function() { this.srd_doc.srd_toggleEditPanel(this); }
								}) 
							);
							// Toggle for Location Tracking
							srd_toolsMenu.addChild(
								new dijit.CheckedMenuItem({
									label: "Display Location",
									srd_doc: this,
									onClick: function() { this.srd_doc.srd_toggleLocationTracking(this); }
								}) 
							);
					
							//// Window Menu ////
							this.srd_windowMenu = new dijit.Menu({});
							this.srd_menuBar.addChild(new dijit.PopupMenuBarItem({
								label: "Window",
								popup: this.srd_windowMenu
							}) );
							// LIST EACH WINDOW LAYOUT IN THE WINDOW MENU
							for( tmpId in this.srd_wlayoutArr) {
								this.srd_windowMenu.addChild( new dijit.MenuItem( {
									label: this.srd_wlayoutArr[tmpId].name,
									value: tmpId,
									srd_doc: this,
									onClick: function() { this.srd_doc.srd_changeWindowLayout(this.value); }
								} ) );
							}

							this.srsearch_store = new dojox.data.QueryReadStore( {
								url: "/srsearch/index"
							} );

							// LIVE SEARCH IN MENUBAR
							this.srsearch_box = new dijit.form.ComboBox( {
								id: "srsearch",
								placeHolder: "Search for something",
								store: this.srsearch_store,
								searchAttr: "addy",
								srd_doc: this,
								autoComplete: false,
				//				selectOnClick: true,
								searchDelay: 1000,
								queryExpr: "${0}",
								onChange: function() {
				//						this.value = this.displayedValue;
										console.log("Search Text Clicked:"+this.store.getValue(this.item, "lat") );
										var lat = this.store.getValue(this.item, "lat");	
										var lon = this.store.getValue(this.item, "lon");	
										if(this.srd_doc.selectedView == null) {
											this.srd_doc.selectedView = this.viewArr[0][0];
										}
										this.srd_doc.selectedView.goToPoint(lat,lon);
								}
							} );
							this.srd_menuBar.addChild(this.srsearch_box);
							this.srsearch_box.startup();
							//END SEARCH BAR
							
							//BEGIN USERNAME / Logout Options
							this.srd_userMenu = new dijit.Menu({});
							this.srd_menuBar.addChild(new dijit.PopupMenuBarItem({
								id: "srdLogout",
								label: "Log Out", //this.staticVals.user_title+" "+this.staticVals.user_lastname,
								popup: this.srd_userMenu
							}) );
							this.srd_userMenu.addChild( new dijit.MenuItem( {
								label: "Log Out: "+this.staticVals.user_lastname,
								srd_doc: this,
								onClick: function() { this.srd_doc.logout(); }
							} ) );

							//END PLACING MENU ITEMS, LETS FIRE UP THE MENUBAR!				
							this.srd_menuBar.startup();

						}

						this.srd_container.addChild(this.srd_menuBar);
//					}.bind(this) );
//					return;
},
// END srd_displayMenuBar

srd_toggleLocationTracking : function(menuItem) {
					dojo.addOnLoad( function() {
						if(menuItem.checked == true) {
							console.log("Enabling Location Tracking");
						// ELSE menuItem is NOT CHECKED :
						} else {
							console.log("Disabling Location Tracking");
							

						}
						if(this.selectedView == null) {
							this.selectedView = this.viewArr[0][0];
						}
						this.selectedView.toggleLocationTracking(menuItem.checked);
					return;
					}.bind(this) );
},



srd_toggleEditPanel : function(menuItem) {
					dojo.addOnLoad( function() {
						if(menuItem.checked == true) {
							if(this.srd_toolbar == null) {
								this.srd_toolbar = new dijit.layout.BorderContainer({ 
				//					style: "background-color:gray;width:150px;border:3px",
									style: "width:150px;overflow:auto;",
									region: 'right',
									splitter: 'true' 
								}  );		
								this.srd_container.addChild(this.srd_toolbar);
								this.srd_container.resize();

				/*
								var cp_tool_panel = new dijit.layout.ContentPane({
									region: 'bottom',
									style: "height:100px",
									content:"<div id='srd_tool_panel' class='olControlPanel' ></div>" } );

								this.srd_toolbar.addChild(cp_tool_panel);

							this.srd_panel = new OpenLayers.Control.Panel( { div: dojo.byId('srd_tool_panel')  } );
							this.map.addControl(this.srd_panel);
				*/
							this.srd_toolbar.resize();


							// BEGIN LAYER SELECT
							var editPaletteTop = new dijit.layout.BorderContainer( {
				//				style:"height:100px;background-color:yellow",
								style:"height:60px;",
								region: 'top',
								splitter: false
							} );
							

							var activeLayerName = new dijit.layout.ContentPane({
								content:"Editing Palette<br>Layer: ",
								region: 'top',
								style:'margin:0px;padding:0px;border:0px;'
				//				style:'height:30px;
							});
							editPaletteTop.addChild(activeLayerName);
							this.srd_layerEditMenu = new dijit.Menu({ });
							for( tmpId in this.srd_layerArr) {
								if( this.srd_layerArr[tmpId].options.type == "Vector" && this.srd_layerArr[tmpId].options.feature_update == "true") {
										this.srd_layerEditMenu.addChild(new dijit.MenuItem( { 
										label: this.srd_layerArr[tmpId].options.name,
										srd_doc: this,
										tmpId: tmpId,
										onClick: function() { this.srd_doc.srd_selectEditLayer( this.tmpId );  }
									} ) );
									// CHECK TO SEE IF selected Layer is null, if so,
									// make the selLayer the last editable layer in the arr
									if(this.srd_selLayer == null) {
										this.srd_selLayer = this.srd_layerArr[tmpId];
					
									}
								}
							}
								
							// AT THIS POINT, if there is it least 1 editable layer, selLayer will NOT
							// be null so if it is it means we don't have ANY editable layers to
							// choose from.
							if(this.srd_selLayer == null ) {
								// MAKE IT SO THAT ALL EDIT CONTROLS ARENT SELECTABLE.
								this.srd_layerEditMenuDropDown = new dijit.form.DropDownButton({
									label: "No Layers Available to Edit",
									dropDown: this.srd_layerEditMenu,
									id: "srd_activeLayer",
									region:'top'
								});
							} else {
								this.srd_layerEditMenuDropDown = new dijit.form.DropDownButton({
									label: this.srd_selLayer.options.name,
									dropDown: this.srd_layerEditMenu,
									id: "srd_activeLayer",
									region:'top'
								});
							}
							editPaletteTop.addChild(this.srd_layerEditMenuDropDown);
							editPaletteTop.startup();
							this.srd_toolbar.addChild(editPaletteTop);
							// END LAYER SELECT

				//			this.srd_toolbar.addChild(this.srd_selLayer.editPalette.layoutContainer)
							if(this.srd_selLayer != null) {
								this.srd_selLayer.editPalette.addToContainer(this.srd_toolbar);
								this.srd_selectEditLayer(this.srd_selLayer.options.id);
							}
						} else {
							this.srd_container.addChild(this.srd_toolbar);
							this.srd_container.resize();
						}
						menuItem.checked = true;
				//		this.srd_panel.activate();

				//		this.srd_toolbar.startup();
				//		var theSize = {w:"50%", h:"50%" };
				//		this.srd_mapContent.resize(theSize);
				//		this.map.updateSize();
				//		this.srd_toolbar.placeAt(dojo.body());		
					} else {
						if(this.srd_toolbar != null) {
					//		this.srd_panel.deactivate();
							this.srd_container.removeChild(this.srd_toolbar);
				//			this.srd_toolbar.destroyRecursive();
							this.srd_container.resize();
				//			delete this.srd_toolbar;
				//			this.srd_toolbar = null;

						}
						menuItem.checked = false;
					}
					return;
					}.bind(this) );
},

saveLayer : function( layerId ) {
					var formatGml = new OpenLayers.Format.GML( { 
							'internalProjection' : new OpenLayers.Projection("EPSG:900913"),
							'externalProjection' : new OpenLayers.Projection("EPSG:4326")
					});
					var test = formatGml.write(this.srd_layerArr[layerId].layer.features);

					dojo.xhrPost( { 
						url: "lib/srd_php/UploadLayer.php",
						content: {
							fileName: this.srd_layerArr[layerId].name,
							localSave:true,
							layerData: test
						},
						load: function(result) {
							console.log("Sent file: "+this.content.fileName);
							document.location.href = "lib/srd_php/UploadLayer.php?fileName="+this.content.fileName;
						}
					} );
},

openFile : function() {
				//	dojo.addOnLoad(function() {
						if(this.fileSelDialog != null) {
							this.fileSelDialog.show();
							return;
						}

					this.fileSelDialog = new dijit.Dialog( {
							style: "width: 400px",
							content:"<div id='test1'></div><div id='test2'></div>"
						} );

					this.openFileForm = new dijit.form.Form( { 
							action:'lib/srd_php/UploadFile.php',
							method: 'post',
							encType:"multipart/form-data"
						} );
					this.openFileForm.placeAt('test1');	

					this.srd_uploader = new dojox.form.Uploader( { 
						id: "uploader",
						type: 'file',
						name: 'uploadedfile',
						label:"Select Layers to Upload",
						multiple:true,
				//		uploadOnSelect:true,
						srd_doc: this,
						onComplete: function(evt) {
				//			alert("Completed file upload!");
							for(var fileArr in evt) {
								if(evt[fileArr].name != null) {
									var theName = evt[fileArr].name;
									var theUrl = "/srd_uploads/"+theName;
				//				console.log("File uploaded! "+theName);	
									theName = theName.replace('.gml','');
									this.srd_doc.srd_createLayer(theName,theUrl);					
								}
							}
						},
						onError: function(evt) {
							alert("File upload error!");
						}

					}  );
					this.srd_fileList = new dojox.form.uploader.FileList({uploader: this.srd_uploader }  );

						var oFSubmit = new dijit.form.Button( {
							label : 'Upload!',
							type  : 'button',
							srd_doc: this,
							onClick: function(evt) {
									console.log("Clicked the Upload Button!");
									this.srd_doc.srd_uploader.upload();					
									this.srd_doc.fileSelDialog.hide();

								}


						});
						
						this.openFileForm.domNode.appendChild(this.srd_fileList.domNode);
						this.openFileForm.domNode.appendChild(this.srd_uploader.domNode);
						this.openFileForm.domNode.appendChild(oFSubmit.domNode);
						this.openFileForm.startup();

					this.fileSelDialog.show();	

				//	} );
					
},


srd_selectEditLayer : function( theId ) {
					console.log("srd_selectEditLayer Called:"+theId);
					if(theId == this.srd_selLayer.id) {
						return;
					}
					if(this.srd_selLayer != null) {
						this.srd_selLayer.editPalette.removeFromContainer(this.srd_toolbar);
						this.srd_selLayer.editPalette.deactivateDrawControls();
					}	
					this.srd_selLayer = this.srd_layerArr[theId];
					this.srd_selLayer.editPalette.addToContainer(this.srd_toolbar);
					this.srd_layerEditMenuDropDown.set("label",this.srd_selLayer.options.name);
					this.srd_selLayer.editPalette.activateDrawControl();
				//	console.log("srd_selectEditLayer Finished");
},
// END selectEditLayer FUNCTION
// BEGIN changeWindowLayout FUNCTION
srd_changeWindowLayout : function(wlayout) {
					if( wlayout != this.selected_wlayout)	{
						console.log("Changing Window Layout from "+this.selected_wlayout+" TO "+wlayout);
						this.selected_wlayout = wlayout;
						if(this.viewContainer) {
							this.viewContainer.destroyRecursive();
						}
						if(this.viewArr) {
							for(var i in this.viewArr) {
								if(this.viewArr[i] != null) {
									for(var j in this.viewArr[i]) {
										this.viewArr[i][j].destroy();
										delete this.viewArr[i][j];
									}	
									delete this.viewArr[i];
								}
							}
							delete this.viewArr;
						}
						this.staticVals.view_layout_x = this.srd_wlayoutArr[this.selected_wlayout].view_x;
						this.staticVals.view_layout_y = this.srd_wlayoutArr[this.selected_wlayout].view_y;
						this.staticVals.view_data = dojo.fromJson( this.srd_wlayoutArr[this.selected_wlayout].view_data);
jonTest1 = this.staticVals.view_data;	
jonTest2 = this.srd_wlayoutArr[this.selected_wlayout].view_data;
						this.staticVals.showname = this.srd_wlayoutArr[this.selected_wlayout].showname;
						this.staticVals.layoutName = this.srd_wlayoutArr[this.selected_wlayout].name;
						
						if( this.srd_wlayoutArr[this.selected_wlayout].theme != null) {
							this.srd_changeTheme(this.srd_wlayoutArr[this.selected_wlayout].theme);
						}		

						this.viewContainer = new srd_gridContainer( {
							nbZones: this.staticVals.view_layout_x,
				//		nbColumns: 2,
							style: "min-width:100%; min-height:100%;",
							isAutoOrganized: true,
//							style: "height:500px",
							hasResizeableColumns : false
						}  );
						dojo.place(this.viewContainer.domNode, this.centerPane.domNode,'first');
						this.viewContainer.startup();
						// NOTE: viewContainer Drag AND Drop DISABLED - Make
						// MENU Item to toggle rearrange mode later...
						this.viewContainer.disableDnd();
						this.viewArr = [];
						for(var xPos=0;xPos<this.staticVals.view_layout_x;xPos++) {
							if( !this.staticVals.view_data[xPos] ) {
								this.staticVals.view_data[xPos] = [];
							}
							var tmpViewYArr = [];
							for(var yPos=0;yPos<this.staticVals.view_layout_y;yPos++) {
								if( !this.staticVals.view_data[xPos][yPos] ) {
									this.staticVals.view_data[xPos][yPos] = [];
									this.staticVals.view_data[xPos][yPos].type = 'empty';
								}
								this.staticVals.view_data[xPos][yPos].xPos = Number(xPos);
								this.staticVals.view_data[xPos][yPos].yPos = Number(yPos);
								this.staticVals.view_data[xPos][yPos].xDim = this.staticVals.view_layout_x;
								this.staticVals.view_data[xPos][yPos].yDim = this.staticVals.view_layout_y;

								tmpViewYArr[yPos] = new window[ this.viewType[this.staticVals.view_data[xPos][yPos].type] ](this.staticVals.view_data[xPos][yPos], this);
								this.selectedView = tmpViewYArr[yPos];
								dojo.connect(tmpViewYArr[yPos], 'onLoad', function() { this.loadedViews(); }.bind(this) );
							}
							this.viewArr[xPos] = tmpViewYArr;
						}
						this.srd_container.resize();
						// BELOW IS CALLED TO PERFORM ACTION WHEN VIEWS ARE DONE BEING CREATED.
						// TODO: LETS FIX IT.
						// UPDATE LINKS IN ALL VIEWS 

					}
},
// END changeWindowLayout FUNCTION

// BEGIN changeViewGridDimensions FUNCTION
srd_changeViewGridDimensions : function(theDimType,theDim) {
					console.log("changeViewGridDim called: "+theDimType+", val: "+theDim);
					if(theDimType == 'x') {
						if(theDim > this.staticVals.view_layout_x) {
							this.viewContainer.setColumns(theDim);
							for(var xPos=this.staticVals.view_layout_x;xPos<theDim;xPos++) {
								if( !this.staticVals.view_data[xPos] ) {
									this.staticVals.view_data[xPos] = [];
								}
								var tmpViewYArr = [];
								for(var yPos=0;yPos<this.staticVals.view_layout_y;yPos++) {
									if( !this.staticVals.view_data[xPos][yPos] ) {
										this.staticVals.view_data[xPos][yPos] = [];
										this.staticVals.view_data[xPos][yPos].type = 'empty';
										this.staticVals.view_data[xPos][yPos].xPos = Number(xPos);
										this.staticVals.view_data[xPos][yPos].yPos = Number(yPos);
										this.staticVals.view_data[xPos][yPos].xDim = this.staticVals.view_layout_x;
										this.staticVals.view_data[xPos][yPos].yDim = this.staticVals.view_layout_y;
										tmpViewYArr[yPos] = new srd_view(this.staticVals.view_data[xPos][yPos], this);
									} else {
										tmpViewYArr[yPos] = this.viewArr[xPos][yPos];
										thmpViewYArr[yPos].resize();
									}
								}
								this.viewArr[xPos] = tmpViewYArr;
							}
						} else {
							//PRESENT WARNING ABOUT LOSING VIEWS...
						}
						this.staticVals.view_layout_x = theDim;
					} else if(theDimType == 'y') { 
						if(theDim > this.staticVals.view_layout_y) {
				//			this.viewContainer.setColumns(theDim);
							for(var xPos=0;xPos<this.staticVals.view_layout_x;xPos++) {
								if( !this.staticVals.view_data[xPos] ) {
									this.staticVals.view_data[xPos] = [];
								}
								if( !this.viewArr[xPos] ) {
									this.viewArr[xPos] = [];
								}
								for(var yPos=0;yPos<theDim;yPos++) {
									if( !this.staticVals.view_data[xPos][yPos] ) {
										this.staticVals.view_data[xPos][yPos] = [];
										this.staticVals.view_data[xPos][yPos].type = 'empty';
									}
									this.staticVals.view_data[xPos][yPos].xPos = Number(xPos);
									this.staticVals.view_data[xPos][yPos].yPos = Number(yPos);
									this.staticVals.view_data[xPos][yPos].xDim = this.staticVals.view_layout_x;
									this.staticVals.view_data[xPos][yPos].yDim = theDim;
									if(!this.viewArr[xPos][yPos]) {
										this.viewArr[xPos][yPos] = new srd_view(this.staticVals.view_data[xPos][yPos], this);
									} else {
											this.viewArr[xPos][yPos].resize(this.staticVals.view_data[xPos][yPos]);
									}
								}
							}
						} else {
							//PRESENT WARNING ABOUT LOSING VIEWS...
						}
						this.staticVals.view_layout_x = theDim;
					}
					this.srd_container.resize();
},

srd_updateViewMenu : function() {
					var theLabel = "Selected View : ";
					if(this.selectedView) {
						theLabel = theLabel+this.selectedView.data.type+" "+this.selectedView.data.xPos+","+this.selectedView.data.yPos;
					}
					this.srd_viewMenuSelected.set('label', theLabel);
},


srd_changeViewType : function(theType) {
					if(this.selectedView && this.selectedView.data.type != theType) {	
						var xPos = this.selectedView.data.xPos;
						var yPos = this.selectedView.data.yPos;
						this.selectedView.destroy();
						delete this.viewArr[xPos][yPos];
						for(var theVar in this.viewDefaults[theType] ) {
							this.staticVals.view_data[xPos][yPos][theVar] = this.viewDefaults[theType][theVar];
						}
							console.log( "NEW VIEW TYPE:"+this.viewType[theType]+"xPos="+xPos+",yPos="+yPos);
							this.viewArr[xPos][yPos] = new window[ this.viewType[theType] ](this.staticVals.view_data[xPos][yPos], this);
						this.selectedView = this.viewArr[xPos][yPos];
						this.viewContainer.resize();
					}
},
// BEGIN getView FUNCTION
getView : function(theId) {
					for(var xPos=0;xPos<this.staticVals.view_layout_x;xPos++) {
						if( this.staticVals.view_data[xPos] ) {
							for(var yPos=0;yPos<this.staticVals.view_layout_y;yPos++) {
								if( this.staticVals.view_data[xPos][yPos] ) {
									console.log("getView called, iterating through with:"+theId+", at x="+xPos+", y="+yPos+"data.id="+this.staticVals.view_data[xPos][yPos].id);
									if(this.staticVals.view_data[xPos][yPos].id == theId) {
										console.log("getView called found "+theId+" at x="+xPos+", y="+yPos);
										return this.viewArr[xPos][yPos];
									}
								}
							}
						}
					}
					return null;
},	
// END getView FUNCTION
// FUNCTION TO DO ANY CLEAN UP NEEDED 
// THEN REDIRECT TO /login/logout
logout : function() {	
					console.log("Logging Out User : "+this.staticVals.user_lastname);
					window.location.href = "/resources/j_spring_security_logout";	

},
	
// BEGIN toggleLayoutNameDisplay
updateLayoutNameDisplay : function() {
					// CHECK IF showname is set to true and if so display layout name.

					if(this.nameCP != null) {
						this.srd_container.removeChild(this.nameCP);
						this.nameCP.destroy();
						delete this.nameCP;
					}

					if( this.staticVals.showname != -1) {
						var theWindowLabel = '';
						if(this.staticVals.showname == -2) {
							theWindowLabel = this.staticVals.layoutName;
						} else if( this.staticVals.showname == -3) {

	
						} else {
							for(var xPos in this.viewArr) {
								for(var yPos in this.viewArr[xPos]) {
									var viewLabel = this.viewArr[xPos][yPos].getLabel();
									if(theWindowLabel && viewLabel) {
										theWindowLabel = theWindowLabel+' | '+viewLabel;
									} else if (viewLabel) {
										theWindowLabel = viewLabel;
									}
								}
							}
						}
						this.nameCP = new dijit.layout.ContentPane( {
							content: '<img src="'+this.siteLeftImage+'" class="srdLayoutImage"/><div id="srdLayoutText">'+theWindowLabel+'</div><img src="'+this.siteRightImage+'" class="srdLayoutImage"/>',
							'class': "srdLayoutName",
							region: 'bottom'
						});

						this.srd_container.addChild(this.nameCP);

					}
	
				return;
},
// END toggleLayoutNameDisplay
			

// BEGIN toggleAutoRefresh
toggleAutoRefresh : function() {
	if(this.staticVals.autoRefresh != null ) {
		if(this.staticVals.autoRefresh == true) {
			this.srd_timer = new dojox.timing.Timer(5000);
			this.srd_timer.onTick = function() { 
				this.sendRefresh();
			}.bind(this);
			this.srd_timer.start();
		} else {
			this.srd_timer.stop();
			delete this.srd_timer;	
		}
	}
	return;
},
// END toggleAutoRefresh

// BEGIN sendRefresh
// ITERATE THROUGH ALL VIEWS AND SEND REFRESH CMD TO sr_layers.
sendRefresh : function() {
		console.log("SRD DOC sendRefresh Called!");
		for( var i in this.srd_layerArr) {
//			console.log("Refresh LayerID:"+i);
			if( this.srd_layerArr[i].options &&  this.srd_layerArr[i].options.layerFormat == "SRJSON") {
//				console.log(" Actual ID:"+this.srd_layerArr[i].options.id+"FORMAT="+this.srd_layerArr[i].options.layerFormat);
				if( this.srd_layerArr[i].layer && this.srd_layerArr[i].layer.visibility == true ) {
//					console.log(" Visibility:"+this.srd_layerArr[i].layer.visibility );
					this.srd_layerArr[i].refresh();
				}
			}
		}
		return;
},
// END sendRefresh

// BEGIN srd_changeTheme
srd_changeTheme : function( theThemeId) {
	console.log("SRD DOC srd_changeTheme Called!");
	var currentTheme = dojo.attr(dojo.body(), 'class');
	if(this.srd_themeArr[theThemeId] != null && currentTheme != this.srd_themeArr[theThemeId].classType ) {
		dojo.attr(dojo.body(),'class',this.srd_themeArr[theThemeId].classType );
		this.srd_container.resize();
	}
	return;
},
// END srd_changeTheme

// BEGIN loadedViews
loadedViews : function() {
	console.log("SRD DOC loadedViews Called!");
	var allViewsLoaded = true;
	for(var tmpX in this.viewArr) {
		for(var tmpY in this.viewArr[tmpX] ) {
			console.log("loadedViews Processing: "+tmpX+" : "+tmpY);
			if(this.viewArr[tmpX][tmpY].loaded != true) {
				allViewsLoaded = false;
				console.log("loadedViews FALSE!");
			}
		}
	}	
	if( allViewsLoaded == true ) {
		console.log("loadedViews: ALL VIEWS LOADED!");
		dojo.ready(function() {
		for(var tmpX in this.viewArr) {
			for(var tmpY in this.viewArr[tmpX] ) {
				this.viewArr[tmpX][tmpY].updateViewLinks();
				this.viewArr[tmpX][tmpY].loadingComplete();
			}
		}
			this.updateLayoutNameDisplay();
		}.bind(this) );
	}	
	return;
},
// END loadedViews
	
	//BEGIN setStatus
	setStatus : function(status) {
		if(status == 'ONLINE') {
			console.log("############# We're ONLINE!");
			this.status = 'ONLINE';
		}
		
	},
	//END setStatus
	//BEGIN getStatus
	getStatus: function() {
		return this.status;
	},
	//END getStatus
	
	// BEGIN accessDataFunc
	accessDataFunc: function (data, accessor) {
	    var keys = accessor.split('.'),
	        result = data;

	    while (keys.length > 0) {
	        var key = keys.shift();
	        if (typeof result[key] !== 'undefined') {
	            result = result[key];
	        }
	        else {
	            result = null;
	            break;
	        }
	    }

	    return result;
	}
	
} );

} );





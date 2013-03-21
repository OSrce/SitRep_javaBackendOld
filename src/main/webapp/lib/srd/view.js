// view.js
////////////////////////////////
// NEW BASE CLASS FOR views 
//
//
//
//
//
/////////////////////////////////

define([ 
	"dojo/_base/declare",
	"srd/include_view",
	"dojo/_base/lang",
	"dijit/layout/BorderContainer",
	"dijit/Menu",
	"dijit/PopupMenuItem",
	"dojox/grid/EnhancedGrid",
	"dojox/grid/enhanced/plugins/NestedSorting",
	"dojox/grid/enhanced/plugins/Pagination"
], function(declare ) {
		return declare( [], {
		// Pointer to parent srd_document class
		srd_doc : null,
		// type: this can be one of the following:
		// empty, map, datagrid, admin, view (more to come)
		type : 'empty',
		data : {},
		contentPane: null,
//		outsideContainer : null,
//		insideContainer : null,
		selected : null,
//		containerStyle : null,
		loaded : false,
		dataMenu : null,
//		id : null,
//		linkViewArr : null,
		//CONSTUCTOR
		constructor : function( data, parent_srd_doc) {
			console.log("srd_view constructor called!");
			this.srd_doc = parent_srd_doc;
			this.data = data;
			if(this.type == 'empty' ) {
				this.contentPane = new dijit.layout.ContentPane({ } );
			}
		}
	} );
} );
		
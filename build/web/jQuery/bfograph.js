// $Id: bfograph.js 13783 2011-09-12 16:57:44Z mike $
//
// This file contains JavaScript functions that can be used to create
// interactive graphs. The JavaScript is used for both interactive
// bitmap graphs (image rollovers) and SVG.
// 
// The first three functions are required for correct display of values on
// Line series. They should not be altered.
// 
// The remaining functions can be used to create "tooltip" type windows
// in rollovers, but are not required for proper functioning and may be
// modified or deleted as required.
// 
// Methods in this file have been tested with Firefox, Safari 2.0 and IE6.
// The SVG code doesn't yet work on Safari, which appears to have trouble
// loading externally references JavaScript files from an SVG.
// 
// All code in this file is under the Public Domain, and end users may do
// anything they like with it.


function isIE() {
    return /msie/i.test(navigator.userAgent) && !/opera/i.test(navigator.userAgent);
}

/**
 * Given a date as a numeric value as used inside the Graph library, and an
 * optional format in the same format as the java.util.SimpleDateFormat class,
 * return the date formatted as a String
 *
 * date   - the date as a numeric. Usually the value of "seriesx"
 * format - (optional) the format to use to format it - defaults to "dd-MMM-yyyy"
 */
function bfgToDate(date,format) {
    if (date!=date) return null;
    date = new Date((date+631152000)*1000);
    var MONTH_NAMES=new Array('January','February','March','April','May','June','July','August','September','October','November','December','Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
    var DAY_NAMES=new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sun','Mon','Tue','Wed','Thu','Fri','Sat');

    if (!format) format="dd-MMM-yyyy";
    var y=date.getYear()+"";
    var M=date.getMonth()+1;
    var d=date.getDate();
    var E=date.getDay();
    var H=date.getHours();
    var m=date.getMinutes();
    var s=date.getSeconds();

    var value=new Object();
    if (y.length < 4) y=""+(y-0+1900);
    value["y"]=""+y;
    value["yyyy"]=y;
    value["yy"]=y.substring(2,4);
    value["M"]=M;
    value["MM"]=M<10 ? "0"+M : M;
    value["MMMM"]=MONTH_NAMES[M-1];
    value["MMM"]=MONTH_NAMES[M+11];
    value["d"]=d;
    value["dd"]=d<10 ? "0"+d : d;
    value["E"]=DAY_NAMES[E+7];
    value["EE"]=DAY_NAMES[E];
    value["H"]=H;
    value["HH"]=H<10 ? "0"+H : H;
    value["h"] = H>=12 ? H-12 : H;
    value["hh"]=value["h"]<10 ? "0"+value["h"] : value["h"];
    value["K"]=H>11 ? H-12 : H;
    value["k"]=H+1;
    value["KK"]=value["K"]<10 ? "0"+value["K"] : value["K"];
    value["kk"]=value["k"]<10 ? "0"+value["k"] : value["k"];
    value["a"]=H>11 ? "PM" : "AM";
    value["m"]=m;
    value["mm"]=m<10 ? "0"+m : m;
    value["s"]=s;
    value["ss"]=s<10 ? "0"+s : s;

    var i=0;
    var result="";
    while (i<format.length) {
        var c=format.charAt(i);
        var token="";
        while (i<format.length && c==format.charAt(i)) {
            token += format.charAt(i++);
        }
        result += value[token]==null ? token : value[token];
    }
    return result;
}

/**
 * Given an X and an array of 4-tuple values of the form (x1,y1,x2,y2),
 * use x to find the matching tuple and return a value which depends on
 * the mode. Called internally, no need for users to call it.
 *
 * x - the X co-ordinate
 * a - the array of co-ordinates
 * mode - given the appropriate tuple, 0 to return the interpolated value y'
 *        between y1 and y2, 1 to return y1 and 2 to return x1.
 */
function bfgFunc(x, a, mode) {
    for (var i=0;i<a.length;i+=4) {
        if (x>=a[i] && x<=a[i+2]) {
            if (mode==1) {
                return (x-a[i])/(a[i+2]-a[i]) < 0.5 ? a[i+1] : a[i+3];
            } else if (mode==2) {
                return (x-a[i])/(a[i+2]-a[i]) < 0.5 ? a[i] : a[i+2];
                return a[i];
            } else {
                return (((x-a[i])/(a[i+2]-a[i])) * (a[i+3]-a[i+1])) + a[i+1];
            }
        }
    }
    return NaN;
}

/**
 * Round val to the specified number of decimal places
 * val the value to round
 * dp the number if decimal places to use
 */
function bfgRound(val, dp) {
    if (dp<=0) {
        return Math.round(val);
    } else {
        var z = ""+val;
        var ix = z.indexOf(".");
        return z.substring(0, Math.min(ix+dp+1, z.length));
    }
}


// ----------- Remaining functions are convenient ways to use -------------
//----------- popup windows with SVG and HTML images.        -------------
//----------- They can be altered or removed as necessary.   -------------


var bfgHider;
var bfgHiderFunc;

//new function that takes an event for identifying SVG elements
function bfgIsSVG(event) {
    if (event && event.view) {		
        // Firefix, Safari, Opera etc
        return event.target.ownerSVGElement; // safari doesn't know what an SVGDocument is
    }
    else if (event && event.currentTarget) {
        // IE
        return event.currentTarget.viewportElement && event.currentTarget.viewportElement.nodeName == "svg";
    }
}

function bfgShowPopup(msg, event) {
    if (bfgHiderFunc) {
        // hide exiting popup
        //setTimeout(bfgHiderFunc, 0);
    }
    if (bfgIsSVG(event)) {
        var svgdoc = (event.view) ? event.view.document: event.currentTarget.ownerDocument;

        if (!svgdoc.getElementById("bfgPopup.rect")) {
            var popupText = svgdoc.createElementNS("http://www.w3.org/2000/svg", "text");
            popupText.setAttribute("id", "bfgPopup.text");
            popupText.setAttribute("visibility", "hidden");
            popupText.setAttribute("fill", "black");
            popupText.setAttribute("font-family", "sans-serif");
            popupText.setAttribute("font-size", 10);
            popupText.appendChild(svgdoc.createTextNode("label"));

            var popupRect = svgdoc.createElementNS("http://www.w3.org/2000/svg", "rect");
            popupRect.setAttribute("id", "bfgPopup.rect");
            popupRect.setAttribute("visibility", "hidden");
            popupRect.setAttribute("fill", "lightyellow");
            popupRect.setAttribute("height", "15");
            popupRect.setAttribute("stroke", "black");
            popupRect.setAttribute("stroke-width", "0.5");

            // Add them to the first group
            var firstGroup = svgdoc.getElementsByTagName("g").item(0);
            firstGroup.appendChild(popupRect);
            firstGroup.appendChild(popupText);
        }

        var popupRect = svgdoc.getElementById("bfgPopup.rect");
        var popupText = svgdoc.getElementById("bfgPopup.text");
        // Get the title attribute from the firt node of the class
        //
        var child = popupText.firstChild;
        if (child.data != msg) {
            popupText.replaceChild(svgdoc.createTextNode(msg), child);
        }

        // create function to hide popup
        bfgHiderFunc = function() {
            popupRect.setAttribute("visibility", "hidden");
            popupText.setAttribute("visibility", "hidden");
            bfgHiderFunc = null;		
        };

        var x = event.clientX;
        var y = event.clientY;
        popupRect.setAttribute("x", x+10);
        popupRect.setAttribute("y", y);
        popupRect.setAttribute("width", msg.length*6);      // TODO Could be more accurate?
        popupText.setAttribute("x", x+15);
        popupText.setAttribute("y", y+10);
        popupText.setAttribute("visibility", "visible");
        popupRect.setAttribute("visibility", "visible");
    } else {
        var popup = document.getElementById("bfgPopup");
        if (popup==null) {
            popup = document.createElement("div");
            popup.style.position = "absolute";
            popup.id = "bfgPopup";
            var body = document.getElementsByTagName("body")[0];
            if (body==null) {               // No body, try appending to document
                body = document;
            }
            body.appendChild(popup);
        }

        // create function to hide popup
        bfgHiderFunc = function() {
            document.getElementById("bfgPopup").style.visibility = "hidden";	
            bfgHiderFunc = null;	
        };

        // Here are the values we can expect to be set in the four main browsers:
        // 				pageY	clientY		documentElement.scrollTop	body.scrollTop
        // Safari (quirks/strict)	page	window		set				set
        // Mozilla quirks		page	window		set				set
        // Opera (quirks/strict)	page	window		set				set
        // Mozilla strict		page	window		set				0
        // IE6 quirks			undef	window		0				set
        // IE6 strict			undef	window		set				0
        //
        var x = event.pageX ? event.pageX : event.clientX + document.documentElement.scrollLeft + document.body.scrollLeft;
        var y = event.pageY ? event.pageY : event.clientY + document.documentElement.scrollTop  + document.body.scrollTop;
        popup.innerHTML="<div style='padding:2px; border:1px solid #000; background-color:#ffd; white-space:nowrap; font:8pt sans-serif'>"+msg+"</div>";
        popup.style.left = (x+15)+"px";
        popup.style.top = (y-5)+"px";
        popup.style.visibility = "visible";
    }
    if (bfgHider) clearTimeout(bfgHider);
}

function bfgHidePopup() {
    if (bfgHiderFunc) {
        bfgHider = setTimeout(bfgHiderFunc, 500);
    }
}


//-------------------------------------------------------------------------
//Following methods apply to AJAX graphs

function _bfoGraphImportNode(node) {
    if (node.nodeType==1) {
        var out = document.createElement(node.nodeName);
        for (var i=0;node.attributes && i<node.attributes.length;i++) {
            var a = node.attributes[i];
            out.setAttribute(a.nodeName, node.getAttribute(a.nodeName));
        }
        for (var i=0;node.childNodes && i<node.childNodes.length;i++) {
            out.appendChild(_bfoGraphImportNode(node.childNodes[i]));
        }
        return out;
    } else if (node.nodeType==3) {
        return document.createTextNode(node.nodeValue);
    } else if (node.nodeType==8) {
        return document.createComment(node.nodeValue);
    }
}

function _bfoGraphConnect(node) {
    var request = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("MSXML2.XMLHTTP");
    if (node.getAttribute("url")!=null) {
        var url = node.getAttribute("url")+"/xml-html";
        request.open("POST", url, true);
        request.setRequestHeader("Content-Type", "text/xml");
        node.removeAttribute("url");
        node.setAttribute("xmlns:bfg", "http://bfo.co.uk/ns/graph?version=2");
        var text = "<?xml version=\"1.0\"?>\n<"+node.tagName.toLowerCase();
        for (var i=0;i<node.attributes.length;i++) {
            var a = node.attributes[i];
            text += " "+a.nodeName+"=\""+a.nodeValue+"\"";
        }
        text += ">"+node.innerHTML+"</"+node.tagName.toLowerCase()+">\n";
        request.send(text);

        request.onreadystatechange = function() {
            if (request.readyState == 4) {
                if (request.responseText) {
                    var doc;
                    if (window.ActiveXObject) {
                        doc = new ActiveXObject("Microsoft.XMLDOM");
                        doc.async = "false";
                        doc.loadXML(request.responseText);
                    } else {
                        doc = (new DOMParser()).parseFromString(request.responseText, "text/xml");
                    }
                    node.parentNode.replaceChild(_bfoGraphImportNode(doc.documentElement), node);
                }
            }
        };
    }
}

/**
 * Only relevant when format="svg-sniff", otherwise server sends OBJECT/EMBED normally
 *
 * SVG plugin for IE needs to use EMBED node not OBJECT, and everything else
 * needs OBJECT, with fallback child EMBED element.
 *
 * Server sends down:
 * <span class='fakeobject'.....
 *    <span class='fakeembed' .....
 * </span>
 *
 * So we need to:
 * - IE: Remove 'fakeobject' nodes, and make 'fakeembed' nodes into <embed> nodes
 * - Everything else: Convert 'fakeobject' to <object> and 'fakeembed' to <embed>
 */
function fixIESVG() {
    if (document.getElementsByClassName == undefined) {
        document.getElementsByClassName = function(className) {
            var hasClassName = new RegExp("(?:^|\\s)" + className + "(?:$|\\s)");
            var allElements = document.getElementsByTagName("*");
            var results = [];

            var element;
            for (var i = 0; (element = allElements[i]) != null; i++) {
                var elementClass = element.className;
                if (elementClass && elementClass.indexOf(className) != -1 && hasClassName.test(elementClass))
                    results.push(element);
            }

            return results;
        };
    }

    var ie = isIE();

    var objects = document.getElementsByClassName('fakeobject');
    for (var i=0;i<objects.length;i++) {
        var obj = objects[i];
        var p = obj.parentNode;
        var emb = obj.getElementsByTagName("span").item(0);

        var e = document.createElement("embed");
        e.setAttribute("id", emb.getAttribute('id'));
        e.setAttribute("src", emb.getAttribute('src'));
        e.setAttribute("width", emb.getAttribute('width'));
        e.setAttribute("height", emb.getAttribute('height'));
        e.setAttribute("pluginspace", emb.getAttribute('pluginspace'));

        if (ie) {
            p.removeChild(obj);
            p.appendChild(e);
        } else {
            var o = document.createElement("object");
            o.setAttribute("id", obj.getAttribute('id'));
            o.setAttribute("type", obj.getAttribute('type'));
            o.setAttribute("codebase", obj.getAttribute('codebase'));
            o.setAttribute("width", obj.getAttribute('width'));
            o.setAttribute("height", obj.getAttribute('height'));
            o.setAttribute("pluginspace", obj.getAttribute('pluginspace'));

            // add embed as child of object
            o.appendChild(e);
            // remove fake elements
            p.removeChild(obj);
            // add new elements
            p.appendChild(o);
        }
    }
}

/**
 * If this method is called after the document is loaded, any <bfg:piegraph>
 * or <bfg:areagraph> tags in the XHTML will be parsed and converted to images.
 * No local server side processing is required, although the "url" attribute of
 * the tag must be set to the URL of the GraphServlet class - eg.
 * <html>
 *  <body>
 *   <bfg:piegraph style="width:400px; height:400px" url="http://bfo.com/servlet/GraphServlet">
 *    <bfg:data>...
 *   </bfg:piegraph>
 *  </body>
 * </html>
 */
function bfoGraphLoader() {
    var kids1 = document.getElementsByTagName("bfg:piegraph");
    var kids2 = document.getElementsByTagName("bfg:axesgraph");
    var kids = new Array();
    for (var i=0;i<kids1.length;i++) {
        kids[kids.length] = kids1[i];
    }
    for (var i=0;i<kids2.length;i++) {
        kids[kids.length] = kids2[i];
    }
    if (kids.length > 0) {
        try {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserRead");
        } catch (e) { }
        for (var i=0;i<kids.length;i++) {
            _bfoGraphConnect(kids[i]);
        }
    }
    fixIESVG();
}

if (window && window.addEventListener) {
    window.addEventListener("load", bfoGraphLoader, false);
} else if (window && window.attachEvent) {
    window.attachEvent("onload", bfoGraphLoader);
}

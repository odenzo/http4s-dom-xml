# HTTP4S V1.xx XML EntityDecoder and EntityEncoder for ScalaJS

## The problem:
scala-xml parsing isn't functional under Scala JS, but the rest (mostly) of scala-xml is. So we replace its JVM Sax parser
with the ScalaJD DOMParser and convert DOMParsers DOM to scala.xml's DOM.

## Design Criteria
+ Handle XML with internal DTD entity refs, but do not allow non-standalone documents (no external calls)
+ Enable the same "shared code" to be used for both JVM and ScalaJS

## Solution

Create XML EntityDecoder and EntityEncoder for XML in namespace `com.odenzo.xxml`.
This has ScalaJS implementation of an implicit EntityEncoder and EntityDecoder that can be imported in shared Scala code.
There is a "no-op stub" on the JVM side, so the existing HTTP4S imports are needed for JVM Decoder/Encoder

+ ScalaJS DOM is used, and handles entities, well-formed XML checked but no DTD validation at all. This allows browser based 
parsing, and NodeJS when the JSDOMNodeJSEnv is used.

```ThisBuild / jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()```

Basically, this parses the XML using the Browser DOM parsing, and then converts the Browser DOM to scala-xml DOM by returning the root elem.
Partially XML documents are not supported, you need a root element.

Once in scala-xml format you can use all the existing x-platform facilities for that, including XPath and node traversals.
If you only have ScalaJS code, the little helper for just the DOM parsing is exposed, and you can extract stuff from the Browser DOM if you 
want. Generally the scala-xml DOM is easier IMHO, although it too is littered with `null` values so be careful.

## Build Details
+ Cross compiled for ScalaJS and Scala 2.13.x and Scala 3.x 
+ TODO: Publish as Scala 3.0 which provides forward compatability

+ Minimal dependencies, only Cats and scala-jsdom 


## Testing
Lost of travails with IJ and ScalaJS in testing, even with SBT.
Instead I have written a "demo" program seperately that does the testing in a real HTTP Client/Server scenario.
Most testing here is in the JVM, since testing in Node is not particularly helpful, and also I am not sure how to test in browser.


Getting dilemma that something is wanting: Node Path and Process imports in jsDom env, but switching to Node env and missing the DOMParser
Even in NodeJS environment if I install jsdom I am having this trouble still. All to test on a simulation of a browser.
Thus the seperate project.

So, these ended up being trial and error, take with a grain of salt.
### Node Setup
I need to manually install jsdom, I used Node 16LTS(Gallium)  (Node17 autoupdated and screwed up everything)
- `npm install jsdom`  
- `npm install jsdom -save-dev`
- `npm install source-map-support`

+ I want to run unit tests under ScalaJS and JVM. JVM is not much problem using MUnit:
  - ThisBuild / jsEnv              := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  - jsEnv                          := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()


Uses NodeJS environment.

npm install jsdom  // Not needed because: libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.1.0"
npm install jks-js



## Example Usage

See etrade-whatever for full example HTTP4S client side usage.

TODO: 
- Minimal Client Example for POSTing XML and receiving XML in return

There is no server example, because there is no ScaleJS env server :-)


## TODO
- Clean up this mess and move to a new repo
- Add invalid XML document suite which should also pass when in no validation mode





Common library project (this is library project that using other project).
For building application need to follow steps:
1. Download and install sbt. (see details on https://www.scala-sbt.org/)
3. After that can be possible working with code using following commands -
    sbt clean     - clean all generated, downnloaded, compiled, or otherwise derived artifacts 
    sbt compile   - compile all sources
    sbt test      - execute all tests
    sbt publishLocal  - generating jar file and placing in .ivy2 local folder. 
    
    For correctly compiling other project you must execute command after any changes in sources:
    
    sbt clean publishLocal
      

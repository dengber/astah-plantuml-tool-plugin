PlantUML tool for Astah
============================
This is an [Astah](http://astah.net/) plug-in which converts Astah diagrams into simple [PlantUML](http://PlantUML.me/) diagrams.

It is drawing PlantUML diagrams by using the service at PlantUML. 

<img src="https://raw.github.com/ChangeVision/astah-PlantUML-tool-plugin/master/src/main/images/screenshot.png" width="720"/>

What is PlantUML?
---
> PlantUML is an open-source tool allowing users to create UML diagrams from a plain text language. The language of PlantUML is an example of a Application Specific Language. It uses Graphviz software to lay out its diagrams. It has been used to allow blind students to work with UML. PlantUML also helps blind software engineers to design and read UML diagrams.

<img src="/src/test/resources/net/astah/plugin/yuml/Sample.png"/>

Available for
---
[Astah Community](http://astah.net/editions/community), [Astah UML](http://astah.net/editions/uml), [Astah Professional](http://astah.net/editions/professional) (6.6 or later)


How to install
---
[Download jar file.](http://astah.change-vision.com/plugins/PlantUML/0.2.0.html)  
Deploy the jar file you downloaded from [PlantUML tool for Astah](https://github.com/ChangeVision/astah-PlantUML-tool-plugin/downloads), in the **"plugins"** folder.

 * e.g.) for Community edition
   
   `$USER_HOME/.astah/community/plugins/`
   
   `/Applications/astah community/plguins/`
   
   `C:\Program Files\astah-community\plugins\`

 * e.g.) for UML edition
   
   `$USER_HOME/.astah/uml/plugins/`
   
   `/Applications/astah UML/plguins/`
   
   `C:\Program Files\astah-UML\plugins\`
   
 * e.g.) for Professional edition
   
   `$USER_HOME/.astah/professional/plugins/`
   
   `/Applications/astah professional/plguins/`
   
   `C:\Program Files\astah-professional\plugins\`

Usage
---
1. Launch Astah
1. Open the .asta file
1. [PlantUML] is added under the [Tool]
1. Select [Tool] - [PlantUML] - [Convert to PlantUML diagrams]
   <img src="https://raw.github.com/ChangeVision/astah-PlantUML-tool-plugin/master/src/main/images/menu.png" width="640"/>
1. Select a Class/Usecase diagram to convert into PlantUML diagram from the diagram list
   <img src="https://raw.github.com/ChangeVision/astah-PlantUML-tool-plugin/master/src/main/images/screenshot.png" width="640"/>

How to build
---
1. Install the Astah Plug-in SDK - <http://astah.net/features/sdk>
1. `git clone git://github.com/ChangeVision/astah-PlantUML-tool-plugin.git`
1. `cd astah-PlantUML-tool-plugin`
1. `astah-build`
1. `astah-launch`

### Generating config files [for Eclipse](http://astah.net/tutorials/plug-ins/plugin_tutorial_en/html/helloworld.html#eclipse)

 * `astah-mvn eclipse:eclipse`

License
---
Copyright 2014 Change Vision, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   <http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

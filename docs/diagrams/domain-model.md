```puml
skinparam monochrome true
skinparam linetype ortho
skinparam nodesep 100
skinparam ranksep 30
hide empty members
hide circles
skinparam component {
  ArrowFontSize 10
}

title Domain Model

class Image{}
class Filter{}
class Importer{}
class ConwayGame{}
class Grid{}
class Cell{}
class Exporter{}

Importer --- Image : uploads > 
Filter -- Image : : applies >q
Image -- ConwayGame : played by >
Image --- ConwayGame : creates <
Image --l Exporter : saves <
ConwayGame -- Grid : has >
Grid -- Image : defined by >
Grid -- Cell : composed by >

```
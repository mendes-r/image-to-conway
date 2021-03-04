```puml
skinparam monochrome true
skinparam linetype ortho
skinparam nodesep 100
skinparam ranksep 30
skinparam component {
  ArrowFontSize 10
}

title Class Diagram

class ConwayGame{}
interface ImageImporter{}
interface ImageExporter{}
class JPGImageImporter{}
class JPGImageExporter{}
class Image{}
class GameGrid{}
class Cell{}
interface Filter{}
class FilterBW


JPGImageImporter ...|> ImageImporter
JPGImageExporter ...|> ImageExporter
ImageImporter --- Image : creates >
ImageExporter --- Image : uses <
Image --- GameGrid : creates <
Image --r Filter : creates <
Filter --- GameGrid : populates >
GameGrid *--- Cell : has >
ConwayGame *--- GameGrid : has >
Filter <|. FilterBW


```
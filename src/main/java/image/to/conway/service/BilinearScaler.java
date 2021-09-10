package image.to.conway.service;

public class BilinearScaler implements ImageScaler{

    @Override
    public void scaleDown(int percentage) {

        // source https://chao-ji.github.io/jekyll/update/2018/07/19/BilinearResize.html

        // Basically if you want to reduce 600x600 to 30x30,
        // you first reduce to 300x300, then 150x150, then 75x75,
        // then 38x38, and only then use bilinear to reduce to 30x30.
    }

    @Override
    public void scaleUp(int percentage){

    }

}

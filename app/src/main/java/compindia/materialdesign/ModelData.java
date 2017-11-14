package compindia.materialdesign;

/**
 * Created by compindi on 09-11-2017.
 */

public class ModelData
{
    int btntxt;
    int btnColor;
    int themeStyle;
    public  ModelData(int btntxt,int btnColor,int theme)
    {
        this.btntxt=btntxt;
        this.btnColor=btnColor;
        this.themeStyle=theme;
    }
    public int getBtntxt()
    {
        return btntxt;
    }
    public int getBtnColor()
    {
        return btnColor;
    }
    public int getThemeStyle()
    {
        return themeStyle;
    }
}

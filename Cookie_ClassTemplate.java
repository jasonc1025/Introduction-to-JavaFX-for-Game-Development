// * Self-Documenting Code: For Immediate Intuitiveness and Long-Term Retention/Maintenance

public class Cookie_ClassTemplate

{
  private String flavor_State;
  private double retail_State;
  
  public Cookie_ClassTemplate(String flavor_In, double retail_In)
  {
    flavor_State = flavor_In;
    retail_State = retail_In;
  }
  
  public String toString(){
    return "flavor_State: " + flavor_State + ", " + "retail_State: " + retail_State;
  }  
}
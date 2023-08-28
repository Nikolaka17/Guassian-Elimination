public class Equation{
  private double[] coefficients;
  private double constant;

  public Equation(double[] a, double c){
    coefficients = a;
    constant = c;
  }

  public double[] getCoefficients(){
    return coefficients;
  }

  public double getConstant(){
    return constant;
  }

  public Equation add(Equation e)throws IndexOutOfBoundsException{
    if(e.getCoefficients().length != coefficients.length){
      throw new IndexOutOfBoundsException("Cannot add equations of differing lengths");
    }
    double[] temp = new double[coefficients.length];
    for(int i = 0; i < coefficients.length; i++){
      temp[i] = coefficients[i] + e.getCoefficients()[i];
    }
    return new Equation(temp, constant + e.getConstant());
  }

  public Equation subtract(Equation e)throws IndexOutOfBoundsException{
    return this.add(e.multiply(-1));
  }

  public Equation multiply(double d){
    double[] temp = new double[coefficients.length];
    for(int i = 0; i < coefficients.length; i++){
      temp[i] = coefficients[i] * d;
    }
    return new Equation(temp, constant * d);
  }

  public Equation divide(double d)throws ArithmeticException{
    if(d == 0){
      throw new ArithmeticException("Cannot divide by zero");
    }
    return this.multiply(1 / d);
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < coefficients.length; i++){
      if(coefficients[i] == 0){
        continue;
      }
      if(i == 0){
        sb.append(coefficients[i]);
      }else{
        sb.append((coefficients[i] < 0)? "- " : "+ " + Math.abs(coefficients[i]));
      }
      sb.append("x" + /*(char)(2081 + i)*/ (i+1) + " ");
    }
    if(sb.toString().equals("")){
      sb.append("0 ");
    }
    sb.append("= " + constant);
    return sb.toString();
  }
}

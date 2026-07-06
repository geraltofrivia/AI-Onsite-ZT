// https://github.com/williamfiset/Algorithms/tree/173fa7ce70f5d731c0f2141b48679e4697a76647/src/main/java/com/williamfiset/algorithms/math/FastFourierTransformComplexNumbers.java#L10-L26
public class TempClass {
  public static Complex[] fft(Complex[] x) {
    int n = x.length;
    if (n == 1) return new Complex[] {x[0]};
    Complex[] arr = new Complex[n / 2];
    for (int k = 0; k < n / 2; k++) arr[k] = x[2 * k];
    Complex[] q = fft(arr);
    for (int k = 0; k < n / 2; k++) arr[k] = x[2 * k + 1];
    Complex[] r = fft(arr);
    Complex[] y = new Complex[n];
    for (int k = 0; k < n / 2; k++) {
      double kth = -2 * k * Math.PI / n;
      Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
      y[k] = q[k].plus(wk.times(r[k]));
      y[k + n / 2] = q[k].minus(wk.times(r[k]));
    }
    return y;
  }

}
package se.raddo.raddose3D;

/**
 * @author Oliver Zeldin
 */

public abstract class CoefCalc {
  public abstract double getDensity();

  @Override
  public abstract String toString();

  public abstract void updateCoefficients(Wedge w, Beam b);

  public abstract double getAbsorptionCoefficient();

  public abstract double getAttenuationCoefficient();

  public abstract double getElasticCoefficient();
}

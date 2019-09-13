///* FeatureIDE - A Framework for Feature-Oriented Software Development
// * Copyright (C) 2005-2017  FeatureIDE team, University of Magdeburg, Germany
// *
// * This file is part of FeatureIDE.
// *
// * FeatureIDE is free software: you can redistribute it and/or modify
// * it under the terms of the GNU Lesser General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * FeatureIDE is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU Lesser General Public License for more details.
// *
// * You should have received a copy of the GNU Lesser General Public License
// * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
// *
// * See http://featureide.cs.ovgu.de/ for further information.
// */
//package org.prop4j.analyses.impl.sat4j;
//
//import org.prop4j.solver.AbstractSatSolver;
//import org.prop4j.solver.ContradictionException;
//import org.prop4j.solver.ISatSolver.SelectionStrategy;
//import org.prop4j.solver.impl.SolverUtils;
//import org.prop4j.solver.impl.sat4j.Sat4jSatSolver;
//import org.prop4j.solverOld.SatInstance;
//
//import de.ovgu.featureide.fm.core.FMCorePlugin;
//import de.ovgu.featureide.fm.core.job.monitor.IMonitor;
//
///**
// * Finds core and dead features.
// *
// * @author Sebastian Krieter
// */
//public class Sat4JConditionallyCoreDeadAnalysisSat extends Sat4JAConditionallyCoreDeadAnalysis {
//
//	public Sat4JConditionallyCoreDeadAnalysisSat(Sat4jSatSolver solver) {
//		super(solver);
//	}
//
//	@Override
//	public int[] analyze(IMonitor monitor) {
//		satCount = 0;
////		solver.getAssignment().ensure(fixedVariables.length);
//		for (int i = 0; i < fixedVariables.length; i++) {
//			try {
//				solver.push(getLiteralFromIndex(fixedVariables[i]));
//			} catch (final ContradictionException e) {
//				FMCorePlugin.getDefault().logError(e);
//			}
//		}
//		solver.setConfiguration(AbstractSatSolver.CONFIG_SELECTION_STRATEGY, SelectionStrategy.POSITIVE);
//		final int[] model1 = SolverUtils.getIntModel(solver.findSolution());
//		satCount++;
//
//		if (model1 != null) {
//			solver.setConfiguration(AbstractSatSolver.CONFIG_SELECTION_STRATEGY, SelectionStrategy.NEGATIVE);
//			final int[] model2 = SolverUtils.getIntModel(solver.findSolution());
//			satCount++;
//
//			// if there are more negative than positive literals
//			if ((model1.length < (countNegative(model2) + countNegative(model1)))) {
//				solver.setConfiguration(AbstractSatSolver.CONFIG_SELECTION_STRATEGY, SelectionStrategy.POSITIVE);
//			} else {
//				solver.setConfiguration(AbstractSatSolver.CONFIG_SELECTION_STRATEGY, SelectionStrategy.NEGATIVE);
//
//			}
//
//			for (int i = 0; i < fixedVariables.length; i++) {
//				model1[Math.abs(fixedVariables[i]) - 1] = 0;
//			}
//
//			SatInstance.updateModel(model1, model2);
//			for (int i = 0; i < model1.length; i++) {
//				final int varX = model1[i];
//				if (varX != 0) {
//					try {
//						solver.push(getLiteralFromIndex(-varX));
//					} catch (final ContradictionException e) {
//						FMCorePlugin.getDefault().logError(e);
//					}
//					satCount++;
//					switch (solver.isSatisfiable()) {
//					case FALSE:
//						solver.pop();
//						try {
//							solver.push(getLiteralFromIndex(varX));
//						} catch (final ContradictionException e) {
//							FMCorePlugin.getDefault().logError(e);
//						}
//						break;
//					case TIMEOUT:
//						solver.pop();
//						break;
//					case TRUE:
//						solver.pop();
//						SolverUtils.updateModel(model1, SolverUtils.getIntModel(solver.findSolution()));
//						solver.pop();
//						break;
//					}
//				}
//			}
//		}
//		return getIntegerAssumptions();
//	}
//
//	@Override
//	public String toString() {
//		return "SAT_Improved";
//	}
//
//}
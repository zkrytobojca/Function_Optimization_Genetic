package pl.edu.pb.wi.ae.evolution.selection;

import pl.edu.pb.wi.ae.evolution.Population;

public interface Selection {
    Population select(Population population);
}

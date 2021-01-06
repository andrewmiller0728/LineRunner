package com.mygdx.linerunner;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Road {

    private ArrayList<RoadSegment> roadSegments;
    private Vector2 endPos;

    public Road(Vector2 initPos, int initSegments, float segmentLen) {
        roadSegments = new ArrayList<>();
        endPos = initPos;
        for (int i = 0; i < initSegments; i++) {
            RoadSegment newSegment = new RoadSegment(segmentLen);
            newSegment.setPosition(endPos.x, endPos.y);
            roadSegments.add(newSegment);
            endPos.add(newSegment.getLength(), 0f);
        }
    }

    public int getSegmentCount() {
        return roadSegments.size();
    }

    public RoadSegment getSegment(int index) {
        return roadSegments.get(index);
    }

}

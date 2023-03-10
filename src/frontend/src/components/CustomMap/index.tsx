import {
  LatLng,
  latLngBounds,
  LeafletEventHandlerFnMap,
  Marker as LeafletMarker,
} from "leaflet";
import { useEffect, useMemo, useRef } from "react";
import { LatLngWithNote } from "react-app-env";
import { Marker, Polyline, Popup, TileLayer, useMap } from "react-leaflet";
import "./index.css";

interface CustomeMapInterface {
  listMarker?: LatLng[] | LatLngWithNote[];
  listLinePoint?: LatLng[];
  setSingleMarker: (marker: LatLng) => void;
  draggable: boolean;
}

function isLatLngWithNote(
  marker: LatLng | LatLngWithNote
): marker is LatLngWithNote {
  return (marker as LatLngWithNote).note !== undefined;
}

const CustomMap = (props: CustomeMapInterface) => {
  const listMarker = props.listMarker;
  const setSingleMarker = props.setSingleMarker;
  const listLinePoint = props.listLinePoint;
  const draggable = props.draggable;
  const draggableMarker = useRef<LeafletMarker>(null);
  const map = useMap();

  const markerHandler = useMemo(() => {
    const handler: LeafletEventHandlerFnMap = {
      dragend() {
        const marker = draggableMarker.current;
        if (marker != null) {
          setSingleMarker(marker.getLatLng());
        }
      },
      mouseover(event) {
        event.target.openPopup();
      },
    };
    return handler;
  }, [setSingleMarker]);

  useEffect(() => {
    if (listMarker && listMarker.length > 0) {
      map.flyToBounds(latLngBounds(listMarker));
    }
  }, [listMarker, map]);

  return (
    <>
      <TileLayer url="https://tile.openstreetmap.org/{z}/{x}/{y}.png" />
      {listMarker
        ? listMarker.map((element: LatLng | LatLngWithNote, index: number) => (
            <Marker
              eventHandlers={draggable ? markerHandler : undefined}
              draggable={draggable}
              ref={draggable ? draggableMarker : undefined}
              key={`marker_${index}`}
              position={element}
            >
              {isLatLngWithNote(element) ? <Popup>{element.note}</Popup> : null}
            </Marker>
          ))
        : null}
      {listLinePoint ? <Polyline positions={listLinePoint} /> : null}
    </>
  );
};

export default CustomMap;

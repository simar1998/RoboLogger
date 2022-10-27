import numpy as np
import cv2


def resize(img):
    return cv2.resize(img, (512, 512))  # arg1- input image, arg- output_width, output_height


class VideoTracking(object):

    def __init__(self, vid_path: str):
        self.vid_path = vid_path

    def init_tracking(self):
        cap = cv2.VideoCapture(self.vid_path)

        ret, frame = cap.read()
        l_b = np.array([0, 230, 170])  # lower hsv bound for red
        u_b = np.array([255, 255, 220])  # upper hsv bound to red

        while ret == True:
            ret, frame = cap.read()

            hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
            mask = cv2.inRange(hsv, l_b, u_b)

            contours, _ = cv2.findContours(mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

            max_contour = contours[0]
            for contour in contours:
                if cv2.contourArea(contour) > cv2.contourArea(max_contour):
                    max_contour = contour

            contour = max_contour
            approx = cv2.approxPolyDP(contour, 0.01 * cv2.arcLength(contour, True), True)
            x, y, w, h = cv2.boundingRect(approx)
            cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 255, 0), 4)

        cv2.imshow("frame", resize(frame))

        cv2.imshow("mask", mask)

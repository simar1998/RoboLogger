import os
import cv2 as cv
from enum import Enum
import uuid


# Enum that stores feature analysis types
class FeatureAnalysis(Enum):
    SIFT = 1
    SURF = 2


class KeypointAnalysis(object):

    def __init__(self, video_source: str, analysis_type: FeatureAnalysis, max_frames=None, frame_extraction_mod=None):
        self.analysis_type = analysis_type
        self.video_source = video_source
        self.max_frames = max_frames
        self.dir_name = str(os.path.join("frames", "ROBOT_FRAMES_") + str(uuid.uuid4()))
        self.__create_dir(self.dir_name)
        self.frame_extraction_mod = frame_extraction_mod
        self.fps = 0
        self.__get_vid_fps()
        self.frames_list = []
        self.analysis_frames = []

    def __generate_frames(self):
        vidcap = cv.VideoCapture(self.video_source)
        success, image = vidcap.read()
        count = 0
        print("Initiating subroutine to break down video frames")
        while success:
            if self.frame_extraction_mod is not None:
                path = os.path.join(self.dir_name, "frame%d.jpg" % count)
                if count % self.frame_extraction_mod == 0:
                    cv.imwrite(path, image)  # save frame as JPEG file
                    self.frames_list.append(str(path))
            else:
                cv.imwrite(path, image)  # save frame as JPEG file
                self.frames_list.append(str(path))
            success, image = vidcap.read()
            count += 1
        print("Video frame extraction complete!")
        vidcap.release()

    def init_analysis(self):
        self.__generate_frames()
        if self.analysis_type == FeatureAnalysis.SIFT:
            self.__sift_analysis()

    def __sift_analysis(self):
        count = 1
        for frame in self.frames_list:
            count = count + 1
            img = cv.imread(frame)
            gray = cv.cvtColor(img, cv.COLOR_BGR2GRAY)
            sift = cv.SIFT_create()
            kp = sift.detect(gray, None)
            img = cv.drawKeypoints(gray, kp, img)
            path = os.path.join(self.dir_name, "analysis_frame%d.jpg" % count)
            self.analysis_frames.append(path)
            cv.imwrite(path, img)

    def __get_vid_fps(self):
        video = cv.VideoCapture(self.video_source)
        (major_ver, minor_ver, subminor_ver) = (cv.__version__).split('.')
        print("Discerning video fps")
        if int(major_ver) < 3:
            self.fps = video.get(cv.CV_CAP_PROP_FPS)
            print("Frames per second using video.get(cv2.cv.CV_CAP_PROP_FPS): {0}".format(self.fps))

        else:
            self.fps = video.get(cv.CAP_PROP_FPS)
            print("Frames per second using video.get(cv2.CAP_PROP_FPS) : {0}".format(self.fps))

        video.release()

    @staticmethod
    def __create_dir(dir_name: str):
        current_directory = os.getcwd()
        final_directory = os.path.join(current_directory, dir_name)
        if not os.path.exists(final_directory):
            os.makedirs(final_directory)

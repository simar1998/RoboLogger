# This is a sample Python script.
from keypoint_analysis import KeypointAnalysis, FeatureAnalysis
from video_tracking import VideoTracking


# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('RoboLogger')
    keypoint = KeypointAnalysis(
        "/home/sim-dev-ubuntu-machine/Documents/code/personal/robots/robot_vids/Einstein Final Tiebreaker - 2022 "
        "FIRST Championship-b56UKoQq8fg.mp4",
        FeatureAnalysis.SIFT, frame_extraction_mod=30, write_anal_images=True)
    #  keypoint.init_analysis()
    tracking = VideoTracking(
        "/home/sim-dev-ubuntu-machine/Documents/code/personal/robots/robot_vids/Einstein Final Tiebreaker - 2022 "
        "FIRST Championship-b56UKoQq8fg.mp4")
    tracking.init_tracking()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/

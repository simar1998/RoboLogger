from moviepy.video.io.ffmpeg_tools import ffmpeg_extract_subclip


class VidUtils(object):

    @staticmethod
    def clip_vid(source_vid, start_time, end_time, target_vid):
        ffmpeg_extract_subclip(source_vid, start_time, end_time, targetname=target_vid)

    

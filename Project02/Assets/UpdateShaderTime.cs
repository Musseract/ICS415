using UnityEngine;

[ExecuteAlways]
public class ShadertoyUpdater : MonoBehaviour
{
    public Material targetMaterial;

    void Update()
    {
        if (targetMaterial)
        {
            targetMaterial.SetFloat("_MyTime", Time.time);
            targetMaterial.SetVector("_Resolution", new Vector4(Screen.width, Screen.height, 0, 0));
        }
    }
}

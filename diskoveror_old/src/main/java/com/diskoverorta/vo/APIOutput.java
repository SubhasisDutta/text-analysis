/*******************************************************************************
 *   Copyright 2016 Serendio Inc. ( http://www.serendio.com/ )
 *   Author - Subhasis Dutta
 *    
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package com.diskoverorta.vo;
import java.util.Set;
//import com.serendio.diskoverer.lifesciences.document.LifeScienceDocument;

public class APIOutput
{
    public EntityAPISet Entity = null;
    //  public LifeScienceDocument entity_lifesciences = null;
    public Set<String> Topics = null;
    public Set<String> Sentiment = null;
    public Set<String> Keywords = null;
    
    public APIOutput()
    {

    }
}
